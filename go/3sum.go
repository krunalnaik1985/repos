package main

import (
  "fmt"
  "sort"
)

//[-1, 0, 1, 2, -1, -4]

func find3Sum(nums []int){
  lenS := len(nums)
  fmt.Println(lenS)
  fmt.Println(nums[0: lenS-2])
  sort.Ints(nums)
  fmt.Println(nums)
  numsMap := make(map[int]int)
  var res [] int
  for i,v := range nums[0: lenS-2] {
    if i >= 1 && nums[i] == nums[i-1] {
      continue
    }
    for _,x := range nums[i: lenS] {
      _, ok := numsMap[x]
      if ok {
        res = append(res, v)
        res = append(res, x)
        res = append(res, -v-x)
      } else {
        numsMap[-x-v] = 1
      }
    }
  }

}


func main(){
  nums := [] int {-1, 0, 1, 2, -1, -4}
  find3Sum(nums)
}
