#print "Hello"



# Your last C/C++ code is saved below:
# #include <iostream>
# using namespace std;

# int main() {
# 	cout<<"Hello";
# 	return 0;
# }

# Ex. d -> d
# 3[ri] -> ririri
# 2[2[ty]a] -> tytyatytya


def getPatter(givenStr):
    open = "["
    close = "]"
    openWords = []
    stack = []
    for i in range(len(givenStr)):
        word = givenStr[i]
        if word.isdigit() and i+1 < len(givenStr) and givenStr[i+1] == open:
            stack.append(int(word))
        if word == close and stack:
            j = i
            if j -1 > 0 and j >= 0:
                word2 = givenStr[j-1]
                while j >= 0:
                    if word2 == open or word2 == close:
                        break
                    j = j -1
                    word2 = givenStr[j-1]
            extract = givenStr[j : i ]
            if openWords:
                temp = openWords[-1] +  extract
                openWords = [temp * stack.pop()]
            else:
                openWords = [extract * stack.pop()]
    if openWords:
        return openWords[-1]
    return ""





print("Output is", getPatter("2[2[ty]a]"))
