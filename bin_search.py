def binsearch(nums,target):
    start = 0
    end = len(nums) - 1
    print end
    while start < end:
        mid = (end + start) / 2
        if nums[0] <= target and target <= nums[mid]:
            print nums[mid]
            end = mid
        else:
            start = mid + 1
    print "here is guess",nums[start:start + 1]


nums = [4 ,5 ,6 ,7 ,0 ,1 ,2]
nums = [1,2,3,4,5,6,7]
binsearch(nums,5)
