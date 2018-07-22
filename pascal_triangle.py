

def triangle(row_count=1):
    length_1 = [1]
    length_2 = [1,1]
    result = []
    result.append(length_1)
    result.append(length_2)
    if row_count >= 3:
        prev_elem = row_count
        for j in range(2, row_count):
            prev_list = result[j-1]
            len_s = len(prev_list)
            temp_list = [1]
            for i in range(len_s):
                item1 = prev_list[i]
                try:
                    value = prev_list[i+1]
                    final_value = item1 + value
                    temp_list.append(final_value)
                except:
                    pass
            temp_list.append(1)
            result.append(temp_list)
    return result


def print_triangle(number):
    getList = triangle(number)
    text = ""
    for i in range(len(getList)):
        tempList = " ".join(str(x) for x in getList[i])
        text = "{0} {1} \n".format(text, tempList)
    print(text)

print_triangle(12)


    
