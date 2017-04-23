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
print threeSum([-1, 0, 1, 2, -1, -4])
