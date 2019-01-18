package main

import "fmt"


func encodeString(givenS string) string {
  start := 0
  var outString string
  for start != len(givenS){
    word := string(givenS[start])
    outValue := checkCompress(word, givenS,start)
    tempString := ""
    if outValue == "" {
      start = start + 1
      tempString = fmt.Sprintf("%s%s", tempString,word)
    } else {
      start = start + 4
      tempString = fmt.Sprintf("%s%s", tempString, outValue)
    }
    outString = fmt.Sprintf("%s%s", outString,tempString) 
  }
  return outString
}

func checkCompress(startWord string, givenS string, startIndex int) string{
  var outValue string
  myWordMap := make(map[string]int)
  if startIndex+ 4 > len(givenS) {
    return outValue
  }
  extractWord := givenS[startIndex : startIndex+ 4]
  
    for _,w1 := range extractWord {
      value, ok := myWordMap[string(w1)]
      if ok {
        myWordMap[string(w1)] = value + 1
      } else {
        myWordMap[string(w1)] = 1
    }  
  }  

  gotValue, ok1 := myWordMap[startWord]
    if ok1 {
      if gotValue == 4 {
        outValue = fmt.Sprintf("%s#4",startWord)
      }
     }
  return  outValue
}


func main(){
  word := "AAAABBBBCCCCC"
  fmt.Println(encodeString(word))
}
