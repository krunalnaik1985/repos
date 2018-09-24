def pow(n1, n2):
  if n2 == 0:
     return 1
  if n2 == 1:
     return n1
  return n1 * pow(n1 * n1, n2 / 2) if n2 % 2 == 1 else pow(n1 * n1, n2 / 2)


def pow2(n1,n2):
    if n2 == 0:
        return 1
    if n2 == 1:
        return n1
    return n1 * pow2(n1, n2-1)

print "is square", pow(2, 4), "number"


print "is square", pow2(2, 4), "number"

def threeSum(nums):
    if len(nums) < 3:
        return []
    nums.sort()
    res = set()
    #import pdb;pdb.set_trace()
    for i, v in enumerate(nums[:-2]):
        if i >= 1 and v == nums[i-1]:
            continue
        d1 = {}
        for x in nums[i+1:]:
            if x not in d1:
                d1[-v-x] = 1
            else:
                res.add((v, -v-x, x))
    return map(list, res)

nums = [-1, 0, 1, 2, -1, -4]
print threeSum(nums)

def steps(step):
    if step == 0:
        return 0
    if step == 1:
        return 1
    if step == 2:
        return 2
    return steps(step-1) + steps(step-2)

print(steps(10))




