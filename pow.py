def pow(n1, n2):
  if n2 == 0:
     return 1
  if n2 == 1:
     return n1
  return n1 * pow(n1 * n1, n2 / 2) if n2 % 2 == 1 else pow(n1 * n1, n2 / 2)


def pow2(n1,n2):
    if n2 == 0:
        return 1
    if n2 == 1:
        return n1
    return n1 * pow2(n1, n2-1)

print "is square", pow(2, 4), "number"


print "is square", pow2(2, 4), "number"

def threeSum(nums):
    if len(nums) < 3:
        return []
    nums.sort()
    res = set()
    #import pdb;pdb.set_trace()
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

nums = [-1, 0, 1, 2, -1, -4]
print threeSum(nums)

def steps(step):
    if step == 0:
        return 0
    if step == 1:
        return 1
    if step == 2:
        return 2
    return steps(step-1) + steps(step-2)



def findNumbers(arrayList, arrayMap, target, count, tempList):
    if arrayList:
        getNumber = arrayList.pop(0)
    else:
        return
    if target == 0:
        print("I am here")
        getList = list(tempList)
        arrayMap[count] = getList
    if target > getNumber:
        target = target - getNumber
        tempList.append((getNumber, "+"))
    else:
        target = target + getNumber
        tempList.append((getNumber, "-"))
    findNumbers(arrayList, arrayMap, target, count, tempList)


def getReverse(word):
    wordSet = list(set(word))
    word = "".join(wordSet)
    low = 0
    high = len(word) -1
    wordList = [x for x in word]
    while low < high:
        temp = wordList[low]
        wordList[low] = wordList[high]
        wordList[high] = temp
        low = low + 1
        high = high - high
    return "".join(wordList)


print getReverse("Essay")



def count(S, m, n1 ):
    #import pdb;pdb.set_trace()

    # If n is 0 then there is 1
    # solution (do not include any coin)
    if (n1 == 0):
        return 1

    # If n is less than 0 then no
    # solution exists
    if (n1 < 0):
        return 0;

    # If there are no coins and n
    # is greater than 0, then no
    # solution exist
    if (m <=0 and n1 >= 1):
        return 0

    # count is sum of solutions (i)
    # including S[m-1] (ii) excluding S[m-1]
    return count( S, m - 1, n1 ) + count( S, m, n1-S[m-1] )


print count([2,3],len([2,3]), 0)


def wordProcess(a1):
    firstWord = None
    for i in range(len(a1)):
        w = a1[i]
        if w != "~":
            if firstWord is None:
                firstWord = i
            else:
                diff = i - firstWord
                if diff % 2 == 0:
                    print("Not Good")
                firstWord = i
    if firstWord











word = "~~ab"
wordProcess(word)