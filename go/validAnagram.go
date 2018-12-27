package main

func isAnagram(s string, t string) bool {
    if len(s) != len(t) {
        return false
    }
    map1 := getMap(s)
    map2 := getMap(t)
    for i, v := range map1 {
        value, ok := map2[i]
        if ok {
            if value != v {
                return false
            }
        } else {
            return false
        }
    }
    return true
}


func getMap(s string) map[string]int {
    mapOne := make(map[string]int)
    count := len(s)
    i := 0
    for i != count{
        word := string(s[i])
        i = i + 1
        value, ok := mapOne[word]
        if ok {
            mapOne[word] = value + 1
        } else {
            mapOne[word] = 1
        }
    
    }
    return mapOne
}
