package main

import (
	"fmt"
	"strings"
)

func getAnagram(s1 string, s2 string) {
	if len(s1) != len(s2) {
		fmt.Println("it is not valid anagram")
	}
	map1 := getMap(s1)
	map2 := getMap(s2)
	count := 0
	var notMatch []string
	for key, val := range map1 {
		foundVal, found := map2[key]
		if found &&  foundVal ==  val{
			count = count + 1
		} else {
			notMatch = append(notMatch, key)

		}
	}

	if count == len(map1) {
		fmt.Println("It is valid Anagram")
	} else {
		if strings.Contains(s1, "*") {
			fmt.Println("I am inside1")
			foundVal, found := map1["*"]
			if found {
				for _, val := range notMatch {
					val2 , ok2 := map2[val]
					if ok2 {
						if val2 != foundVal {
							fmt.Println("It is not valid")
						} else {
							fmt.Println("It is valid")
						}
					}
				}
			}

		}
		if strings.Contains(s2, "*") {
			fmt.Println("I am inside2")
			foundVal, found := map2["*"]
			if found {
				for _, val := range notMatch {
					val2 , ok2 := map1[val]
					if ok2 {
						if val2 != foundVal {
							fmt.Println("It is not valid")
						} else {
							fmt.Println("It is valid")
						}
					}
				}
			}

		}

	}

}

func getMap(s1 string) map[string]int{
	valMap := make(map[string]int)
	for _, val := range s1 {
		tempVal := string(val)
		mVal , ok := valMap[tempVal]
		if ok {
			valMap[tempVal] = mVal + 1
		} else {
			valMap[tempVal] = 1
		}
	}
	return valMap
}

func main(){
	string1 := "iceman"
	string2 := "cinem*"
	getAnagram(string1, string2)
}
