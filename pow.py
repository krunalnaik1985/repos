def pow(n1, n2):
  if n2 == 0:
     return 1
  if n2 == 1:
     return n1
  return n1 * pow(n1 * n1, n2 / 2) if n2 % 2 == 1 else pow(n1 * n1, n2 / 2)

print "is square", pow(5, 4), "number"
