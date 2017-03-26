
def non_repeat(s1):
   dic12 = {}
   len_a = len(s1)
   count = 0
   for i in range(len_a):
      word = s1[i]
      if word not in dic12:
         dic12[word] = (1, i)
      else:
         dic12[word] = (dic12[word][0] + 1, dic12[word][1])
   first_seen = 0
   low = 1000
   for j, k in dic12.iteritems():
      count = k[0]
      index = k[1]
      if count == 1:
         if index < low:
            low = index
   return low if low != 1000 else -1

s1 = "leetcode"
print non_repeat(s1)
s2 = 'loveleetcode'
print non_repeat(s2)
s3 = ''
print non_repeat(s3)
