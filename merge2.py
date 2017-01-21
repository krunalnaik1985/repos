a1 = [1, 2, 3, 4, 5, 6]
b1 = [4, 6, 8, 9, 10, 11]

a_len = len(a1)
b_len = len(b1)
c1 = 0
d1 = 0
e = []
while c1 <= a_len - 1 and d1 <= b_len - 1:
   #  import pdb;pdb.set_trace()
   #  print "i am inside", c1, d1
   if a1[c1] < b1[d1]:
      e.append(a1[c1])
      c1 = c1 + 1
      #  print a1[c1]
   else:
      e.append(b1[d1])
      d1 = d1 + 1

if c1 == a_len:
   for i in range(d1, b_len):
      e.append(b1[i])
else:
   for i in range(c1, a_len):
      e.append(a1[i])

print e
