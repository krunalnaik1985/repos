from access_ec2 import Ec2Helper
import boto
import boto.route53

class AwsAmi(Ec2Helper):
   def __init__(self):
      super(AwsAmi, self).__init__()
      self.conn_route53 = boto.route53.connection.Route53Connection(aws_access_key_id=self.access_key,
                                                                    aws_secret_access_key=self.secret)

   def get_changes(self, zone_id):
      changes = boto.route53.record.ResourceRecordSets(self.conn_route53, zone_id)
      return changes

   def delete_domain(self, state='A', domain_name='', zone_id='', action='DELETE', value='', ttl=600):
      try:
         changes = self.get_changes(zone_id)
         change = changes.add_change(action=action, name=domain_name, type=state, ttl=ttl)
         change.add_value(value)
         changes.commit()
         return True
      except boto.route53.exception.DNSServerError:
         return False
      except boto.exception.TooManyRecordsException:
         return False
      except Exception:
         return False

   def upsert_domain(self, state='A', domain_name='', zone_id='', ip_addr='', action='UPSERT', ttl=600):
      try:
         changes = self.get_changes(zone_id)
         change = changes.add_change(action=action, name=domain_name, type=state, ttl=ttl)
         change.add_value(ip_addr)
         changes.commit()
         return True
      except boto.route53.exception.DNSServerError as e:
         log.exception("Got DNSServerError %s" % e)
         return False
      except boto.exception.TooManyRecordsException as e:
         log.exception("Got TooManyRecordsException %s" % e)
         return False
      except Exception as e:
         log.exception("Unexpected Exception %s" % e)
         return False

   def get_resource_records(self, domain_name, zone_id):
      resouce_record = False
      for rr in self.rrs:
         if rr.name.rstrip('.') == domain_name:
            resouce_record = rr.resource_records[0]
            break
      return resouce_record

   def add_domain(self, state='A', domain_name='', zone_id='', ip_addr='', action='UPSERT', ttl=600):
      status = self.upsert_domain(state=state,
                                  domain_name=domain_name,
                                  zone_id=zone_id,
                                  ip_addr=ip_addr,
                                  action='UPSERT',
                                  ttl=600)
      retry_handler_count = 35
      while retry_handler_count > 0:
         if not status:
            try:
               value = (domain_name.replace('xhr-', '', 1) if domain_name.startswith('xhr-')
                        else socket.gethostbyname(domain_name))
            except:
               value = ''
            self.delete_domain(state=state,
                               domain_name=domain_name,
                               zone_id=zone_id,
                               action='DELETE',
                               value=value,
                               ttl=600)
            #  Five requests per second per AWS account. If you submit more than five requests per second,
            #  Amazon Route 53 returns an HTTP 400 error (Bad request). The response header also includes
            #  a Code element with a value of Throttling and a Message element with a value ofRate exceeded.
            #  http://docs.aws.amazon.com/Route53/latest/DeveloperGuide/DNSLimitations.html
            time.sleep(5 if retry_handler_count < 20 else 3)
            retry_status = self.upsert_domain(state=state,
                                              domain_name=domain_name,
                                              zone_id=zone_id,
                                              ip_addr=ip_addr,
                                              action='UPSERT',
                                              ttl=600)
            retry_handler_count = retry_handler_count - 1
            #  If retry sucessful then break the loop
            if retry_status:
               break
         else:
            time.sleep(5)
