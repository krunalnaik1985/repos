import boto
import boto.ec2
import boto.ec2.autoscale
import boto.ec2.blockdevicemapping
import boto.ec2.elb
import boto.route53
import os
import ConfigParser
import time

class Ec2Helper(object):
   def __init__(self):
      config = ConfigParser.ConfigParser()
      cur = os.path.dirname(os.path.realpath('__file__'))
      path = os.path.join(cur, 'access_key.txt')
      with open(path) as f1:
         config.readfp(f1)
      self.access_key = config.get('default', 'access')
      self.secret = config.get('default', 'secret')

   def get_ec2_reservations(self):
      conn = boto.ec2.connect_to_region('us-east-2',
                                        aws_access_key_id=self.access_key,
                                        aws_secret_access_key=self.secret)
      if not conn:
         raise Exception('EC2 connection is not available')
      reservations = conn.get_all_reservations()
      return reservations

   def get_all_instances(self):
      reservations = self.get_ec2_reservations()
      instances_map = {}
      for each_res in reservations:
         instances_map[each_res] = []
         instances = reservations[0].instances
         inst = instances[0]
         temp = {}
         temp['state'] = inst.state
         temp['ami_id'] = inst.image_id
         temp['type'] = inst.instance_type
         temp['region'] = inst.placement
         temp['ip'] = inst.ip_address
         instances_map[each_res].append(temp)
      print instances_map
      return instances_map

   def perform_action(self, instance_id, action='terminate', check_status=False):
      reservations = self.get_ec2_reservations()
      instances_map = {}
      for each_res in reservations:
         instances = each_res.instances
         for inst in instances:
            if inst.id != instance_id:
               continue
            state = inst.state
            if check_status:
               return state
            if action in state:
               print 'Instance:%s is already in this stage:%s' % (instance_id, action)
               return
            else:
               if action == 'stop':
                  inst.stop()
                  return
               if action == 'terminate':
                  inst.terminate()
                  return
               if action == 'run':
                  inst.start()

   def wait_till_running(self, instance_id):
      current_time = time.time()
      while True:
         run_time = time.time()
         elapse_time = run_time - current_time
         if elapse_time >= 60:
            print "Timing out and instance is not recovered:%s" % instance_id
            raise Exception('Timed Out')
         state = self.perform_action(instance_id=instance_id, check_status=True)
         if state == 'running':
            print "instance_id:%s is in running state" % instance_id
            break
         else:
            time.sleep(1)

def main():
   ec2 = Ec2Helper()
   ec2.get_all_instances()
   #  ec2.perform_action(instance_id='i-071258537d259ec7f', action='stop')
   ec2.perform_action(instance_id='i-071258537d259ec7f', action='run')
   ec2.wait_till_running(instance_id='i-071258537d259ec7f')



if __name__ == '__main__':
   main()
