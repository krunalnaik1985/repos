package main

import (
	"fmt"
	"strings"
)

func Pop(array []string) ([]string,string) {
	l := len(array)
	return array[:l-1], array[l-1]
}

func xmlParser(xmlString string) {
	openBracket := "<"
	closeBracket := ">"
	openNumber := 0
	closeNumber := 0
	var temp string
	var arrayVal []string
	for index,val := range xmlString {
		if string(val) == openBracket {
			openNumber = index+1
		}
		if string(val) == closeBracket {
			closeNumber = index
			tempString := string(xmlString[openNumber:closeNumber])
			if strings.HasPrefix(tempString, "/") {
				if len(arrayVal)  > 0 {
					arrayVal, temp = Pop(arrayVal)
					remove := tempString[1:]
					fmt.Println("Temp String is :", remove)
					if temp != remove {
						fmt.Println("It is not valid")
					}
				} else {
					fmt.Println("It is not valid")
				}
			} else {
				arrayVal = append(arrayVal, tempString)
				openNumber = closeNumber
			}

		}
	}
	fmt.Println(arrayVal)
}

func main(){
	example := "<note><to>Tove</to><from>Jani</from><heading>Reminder</heading><body>Don't forget me this weekend!</body></note>"
	xmlParser(example)
}