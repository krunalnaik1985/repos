import os
import ConfigParser
import subprocess

class BuildAMI(object):
   def __init__(self):
      config = ConfigParser.ConfigParser()
      cur = os.path.dirname(os.path.realpath('__file__'))
      path = os.path.join(cur, 'access_key.txt')
      with open(path) as f1:
         config.readfp(f1)
      self.access_key = config.get('default', 'access')
      self.secret = config.get('default', 'secret')

   def validate_template(self):
      cur = os.path.dirname(os.path.realpath('__file__'))
      template = os.path.join(cur, 'template.json')
      packer_file = os.path.join(cur, 'packer')
      subprocess.check_call([packer_file, 'validate', template])

   def build_ami(self):
      cur = os.path.dirname(os.path.realpath('__file__'))
      template = os.path.join(cur, 'template.json')
      packer_file = os.path.join(cur, 'packer')
      access_key = r"'aws_access_key=%s'" % self.access_key
      secret = r"'aws_secret_key=%s'" % self.secret
      code = os.system('%s build -var %s -var %s %s' % (packer_file, access_key, secret, template))
      if code != 0:
         raise Exception('Found Error while building AMI or installing packages')

def main():
   build_ami = BuildAMI()
   build_ami.validate_template()
   build_ami.build_ami()


if __name__ == '__main__':
   main()
