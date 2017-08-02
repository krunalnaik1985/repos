import os

def find_all_files(file_path, dic):
   files = os.listdir(file_path)
   for each_file in files:
      path = os.path.join(file_path, each_file)
      if os.path.isdir(path):
         find_all_files(path, dic)
      else:
         if file_path in dic:
            dic[file_path].append(path)
         else:
            dic[file_path] = [path]
   return dic

def get_paths():
   cur = os.getcwd()
   dic = {}
   find_all_files(cur, dic)
   for e1, v1 in dic.iteritems():
      print e1, v1

get_paths()
