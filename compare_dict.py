def compare_two_dict(d1, d2):
   if type(d1) == dict and type(d2) == dict:
      keys1 = d1.keys()
      keys2 = d2.keys()
      if not (len(keys1) == len(keys2)):
         if len(keys1) > len(keys2):
            for k1 in keys1:
               if k1 not in keys2:
                  print "This key does not exist: %s" % k1
         else:
            for k2 in keys2:
               if k2 not in keys1:
                  print "This key does not exist: %s" % k2
   for k in d1:
      if k in d2:
         val1 = d1[k]
         val2 = d2[k]
         if type(val1) == dict:
            compare_two_dict(val1, val2)
         elif type(val1) == list:
            if not (set(val1) == set(val2)):
               print "Not maching contents for :key = %s : %s %s" % (k, val1, val2)
         else:
            if not (val1 == val2):
               print "Not maching contents for :key = %s : %s %s " % (k, val1, val2)
      else:
         print "key does not exist in dict2:%s" % k

compare_two_dict(d1, d2)
dict3 = {"a":1, "b":[1, 2, 3], "c":{"d":{"d":1}}}
dict4 = {"a":1, "b":[3, 2, 1, 5], "c":{"d":{"e": {"f": 1}}}}
compare_two_dict(dict3, dict4)
