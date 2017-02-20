def copy_dict(list1):
    dict1 = {}
    for i in range(len(list1)):
        dict1[i] = list1[i]
    return dict1

def groupanagrams(list1):
    dict1 = copy_dict(list1)
    print dict1
    dict2 = copy_dict(list1)
    print dict2
    for j,v in dict2.iteritems():
        dict2[j] = "".join(sorted(v))
    print dict2
    sorted_list = sorted(dict2.values())
    print_arr = []
    for each_value in sorted_list:
        for k,x in dict2.iteritems():
            if k not in print_arr:
                print dict1[k]
                print_arr.append(k)

arr1 = ["cat", "dog", "tac", "god", "act"]
groupanagrams(arr1)
arr2 = ["eat", "tea", "tan", "ate", "nat", "bat"]
groupanagrams(arr2)
