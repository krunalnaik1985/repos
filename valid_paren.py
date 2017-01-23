a1 = '{()}'
a2 = ')))'


def valid_parentheses(a1):
    valid_open = '{(['
    valid_exit = '})]'
    stack_1 = []
    for each_w in a1:
        if each_w in valid_open:
            print "item to add",each_w
            stack_1.append(each_w)
        else:
            if each_w in valid_exit:
                get_index = valid_exit.index(each_w)
                # get correnspiding
                item = valid_open[get_index]
                if stack_1:
                    popped_item = stack_1.pop()
                else:
                    return False
                if not item == popped_item:
                    return False
    if stack_1:
        return False
    return True


print valid_parentheses(a1),"first",
print valid_parentheses(a2),"second"
