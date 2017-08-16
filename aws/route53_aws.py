import boto
import boto.route53
from boto.s3.key import Key
from boto.s3.connection import Location
import ConfigParser
import dateutil.parser as dparser
import logging
import os
from os.path import expanduser
import json
import urlparse
import socket
import shutil
import time

class Route53(object):
   _instance = None

   def __new__(cls, *args, **kwargs):
      if not cls._instance:
         cls._instance = super(Route53, cls).__new__(cls, *args, **kwargs)
      return cls._instance

   def __init__(self):
      """
      Set the AWS Config File so It can connect to Route53 for DNS Management
      """
      aws_id, aws_access_key = None, None
      super(Route53, self).__init__()
      try:
         self.conn_route53 = boto.route53.connection.Route53Connection(aws_access_key_id=aws_id,
                                                                       aws_secret_access_key=aws_access_key)
      except:
         raise Exception("Unable to connect AWS. Please check AWS id and key")

      self.dns_name = None

   def set_dns_name(self, dns_name):
      """
      It will Set DNS Name
      """
      self.dns_name = dns_name

   def get_dns_name(self):
      """
      It will return Current DNS Name
      """
      return self.dns_name

   def get_zones(self):
      """
      It will return DNS Zone on AWS Route53
      """
      return self.conn_route53.get_zones()

   def get_zones_count(self):
      """
      It will Return number of DNS Zone
      """
      return len(self.get_zones())

   def get_changes(self, zone_id):
      """
      It is required to get changes object
      to DELETE or UPSERT any Domain
      """
      changes = boto.route53.record.ResourceRecordSets(self.conn_route53, zone_id)
      return changes

   def get_all_rsets(self, zone_id):
      """
      It will return all Available
      DNS records in given Zone ID
      """
      self.rrs = self.conn_route53.get_all_rrsets(zone_id)
      return self.rrs

   def is_domain_exists(self, domain_name, zone_id):
      """
      TO Verify that DNS Domain in AWS Route53
      ::param zone_id -> String
      """
      found_domain = False
      for rr in self.rrs:
         if rr.name.rstrip('.') == domain_name:
            found_domain = True
            break
      return found_domain

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
