list1 = [20,21,22]
list2 = [1,2,3,4,5,6,7,8,9,10,11,12,13]
# Merge Two Sorted Array
# It can be done for Linked List

len1 = len(list1)
len2 = len(list2)
list3 = []

i1 = 0
i2 = 0
r1 = 0
l1 = 0
while len1 > 0 and len2 > 0:
    if list1[i1] < list2[i2]:
        list3.append(list1[i1])
        i1 = i1 + 1
        len1 = len1 -1
        r1 = r1 + 1
    else:
        list3.append(list2[i2])
        i2 = i2 + 1
        l1 = l1 + 1
        len2 = len2 -1

#print list3

#print len1
#print len2

i2 = 0
while len2 > 0:
    if list2[i2] is not None:
        list3.append(list2[i])
        len2 = len2 - 1
    i2 = i2 + 1

j2 = 0
while len1 > 0:
    if list1[j2] is not None:
        list3.append(list1[j2])
        len1 = len1 - 1
    j2 = j2 + 1

print list3

