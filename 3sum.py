def threeSum(nums):
   len_s = len(nums)
   next_set = []
   found = False
   for i1 in range(len_s):
      for j1 in range(1, len_s):
         answer = nums[i1] + nums[j1]
         perfect_i = int(answer) * -1
         if perfect_i in nums and nums.index(perfect_i) != i1 and nums.index(perfect_i) != j1:
             sum = nums[i1] + nums[j1] + int(perfect_i)
             if sum == 0 and i1 != j1:
                a1 = [nums[i1], nums[j1], int(perfect_i)]
                if len(next_set) == 0:
                   pass
                else:
                   for e1 in next_set:
                      if set(e1) == set(a1):
                         found = True
                         break
                      if [2, 2, -4] == a1:
                         import pdb;pdb.set_trace()
                if not found:
                   next_set.append(a1)
                found = False
   return next_set

def threeSum2(nums):
    if len(nums) < 3:
        return []
    nums.sort()
    import pdb;pdb.set_trace()
    res = set()
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

print threeSum2([-1, 0, 1, 2, -1, -4])
