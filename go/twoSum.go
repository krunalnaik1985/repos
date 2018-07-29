package main

func twoSum(nums []int, target int) []int {
    sumpMap := make(map[int]int)
    var makeList [] int
    for i,_ := range nums {
        comp := target - nums[i]
        val, ok := sumpMap[comp]
        if ok {
            makeList = append(makeList, val)
            makeList = append(makeList, i)
            return makeList
        }
        sumpMap[nums[i]] = i
    }
    return makeList
}

func main(){
  nums := []int{2,7,11,15}
  twoSum(nums, 9)
}
  
