def numDecodings(s1):
    # Write your code here
    if s1 == "" or s1[0] == '0':
        return 0

    dp=[1,1]
    import pdb;pdb.set_trace()
    if s1 == "": return 0
    dp = [0 for x in range(len(s1)+1)]
    dp[0] = 1
    for i in range(1, len(s1)+1):
        if s1[i-1] != "0":
            dp[i] += dp[i-1]
        if i != 1 and s1[i-2:i] < "27" and s1[i-2:i] > "09":  #"01"ways = 0
            dp[i] += dp[i-2]
    return dp[len(s1)]

print numDecodings('123')
