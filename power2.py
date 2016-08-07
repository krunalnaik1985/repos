
def rec(number):
  if number % 2 == 0 and number/2 == 1:
     return 0
  if number % 2 == 1:
     return 1
  return rec(number/2)

for i in range(1,10000):
   print "is square",rec(i),"number",i
