def numDecodings(s1):
    # Write your code here
    if s1 == "" or s1[0] == '0':
        return 0

    dp=[1,1]
    if s1 == "": return 0
    dp = [0 for x in range(len(s1)+1)]
    dp[0] = 1
    for i in range(1, len(s1)+1):
        if s1[i-1] != "0":
            dp[i] += dp[i-1]
        if i != 1 and s1[i-2:i] < "27" and s1[i-2:i] > "09":  #"01"ways = 0
            dp[i] += dp[i-2]
    return dp[len(s1)]

numDecodings('123')


routes = [[1,9,0],
          [1,0,0],
          [1,0,0]]


def count_steps(routes):
    row = len(routes)
    col = len(routes[0])
    print(row,col, routes)
    distance_inst = []
    for i in range(row):
        for j in range(col):
            print("Got Distance",steps(i,j, routes, 0, distance_inst))
    print(distance_inst)


def steps(row, col, routes, distance, distance_inst):
    if row < 0 or col < 0 or col >= len(routes[0]) or row >= len(routes) :
        print("I am inside",row,col, distance)
        return distance
    if routes[row][col] == 9:
        routes[row][col] = 0
        print("Found Distance", distance, routes)
        distance_inst.append(distance)
        return distance
    if routes[row][col] != 1:
        return distance
    routes[row][col] = 0
    distance = distance + 1
    steps(row+1, col, routes, distance,distance_inst)
    steps(row-1, col, routes, distance,distance_inst)
    steps(row, col+1, routes, distance,distance_inst)
    steps(row, col-1, routes, distance, distance_inst)
    return distance

count_steps(routes)

def merge(intervals):
    intervals.sort(key=lambda x: x[0])
    import pdb;pdb.set_trace()

    merged = []
    for interval in intervals:
        # if the list of merged intervals is empty or if the current
        # interval does not overlap with the previous, simply append it.
        if not merged or merged[-1][1] < interval[0]:
            merged.append(interval)
        else:
            # otherwise, there is overlap, so we merge the current and previous
            # intervals.
            merged[-1][1] = max(merged[-1][1], interval[1])
    print(merged)
    return merged

merge([[1, 3], [2, 6], [8, 10], [15, 18]])
