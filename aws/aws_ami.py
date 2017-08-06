from access_ec2 import Ec2Helper
import boto
import boto.ec2
import random
import boto.ec2.blockdevicemapping
from boto.ec2.blockdevicemapping import (BlockDeviceMapping, BlockDeviceType)

class AwsAmi(Ec2Helper):
   def __init__(self):
      super(AwsAmi, self).__init__()
      self.conn = boto.ec2.connect_to_region('us-east-2',
                                             aws_access_key_id=self.access_key,
                                             aws_secret_access_key=self.secret)
      self.as_conn = boto.ec2.autoscale.connect_to_region('us-east-2',
                                                          aws_access_key_id=self.access_key,
                                                          aws_secret_access_key=self.secret)

   def create_security_group(self, group_name):
      sg = self.conn.create_security_group(group_name, group_name)
      return sg

   def authorize_security_group(self, sg_object, list_of_ports, proto='tcp'):
      if sg_object and type(list_of_ports) == list:
         for each_port in list_of_ports:
            if type(each_port) == int:
               sg_object.authorize(ip_protocol=proto, from_port=each_port,
                                   to_port=each_port, cidr_ip='0.0.0.0/0')
            else:
               sg_object.authorize(ip_protocol=proto, from_port=each_port[0],
                                   to_port=each_port[1], cidr_ip='0.0.0.0/0')
         return sg_object.id

   def get_all_images(self, image_name):
      images_inst = self.conn.get_all_images(owners=['self'], filters={'name': image_name})
      if images_inst:
         return images_inst[0].id

   def create_auto_scale_group(self, base_name, security_group, image_id, profile_name,
                               value='krunallinuxvm'):
      bdm = BlockDeviceMapping()
      bdm['/dev/sdb'] = BlockDeviceType(ephemeral_name='ephemeral0')
      bdm['/dev/sdc'] = BlockDeviceType(ephemeral_name='ephemeral1')
      lc = boto.ec2.autoscale.launchconfig.LaunchConfiguration(name=base_name,
                                                               image_id=image_id,
                                                               security_groups=security_group,
                                                               instance_type='t2.micro',
                                                               block_device_mappings=[bdm],
                                                               instance_profile_name=profile_name,)
      self.as_conn.create_launch_configuration(lc)
      name_tag = boto.ec2.autoscale.tag.Tag(key='Name',
                                            value=value,
                                            resource_id=base_name,
                                            propagate_at_launch=True)
      owner_tag = boto.ec2.autoscale.tag.Tag(key='owner',
                                             value='naik@gmail.com',
                                             resource_id=base_name,
                                             propagate_at_launch=True)
      mode_tag = boto.ec2.autoscale.tag.Tag(key='mode', value='test',
                                            resource_id=base_name,
                                            propagate_at_launch=True)
      asg = boto.ec2.autoscale.group.AutoScalingGroup(name=base_name,
                                                      availability_zones=['us-east-2a', 'us-east-2b'],
                                                      default_cooldown=300,
                                                      desired_capacity=1,
                                                      launch_config=lc,
                                                      load_balancers=None,
                                                      max_size=4,
                                                      min_size=1,
                                                      tags=[name_tag, owner_tag, mode_tag],
                                                      termination_policies=["ClosestToNextInstanceHour"],)
      self.as_conn.create_auto_scaling_group(asg)


def main():
   ami_aws = AwsAmi()
   number = random.randrange(1000)
   sg = ami_aws.create_security_group('ssh-%s' % str(number))
   open_ports = [80, 443, (3128, 3131), 9080, (9130, 9131), 9443]
   sg_id = ami_aws.authorize_security_group(sg, open_ports)
   print "created security group id:%s" % sg_id
   ami_id = ami_aws.get_all_images('krunallinux')
   security_groups = [sg]
   ami_aws.create_auto_scale_group('krunalami', security_groups, ami_id, 'krunaladmin')


if __name__ == '__main__':
   main()
