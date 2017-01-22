list1 = [7,1,5,3,6,4]
#list1 = [7, 6, 4, 3, 1]
#list1 = [2,1,2,1,0,1,2]

min_price = 1011111111111
max_profit = 0

len_s1 = len(list1)

for i in list1:
    if i < min_price:
        min_price = i
    else:
        print "Trying to sell stock"
        if i - min_price > max_profit:
            max_profit = i - min_price

print min_price,max_profit
