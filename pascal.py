class Solution(object):
    def generate(self, numRows):
        """
        :type numRows: int
        :rtype: List[List[int]]
        """
        n = numRows
        a1 = [1]
        a2 = [1, 1]
        triangle = [a1, a2]
        nums = []
        for j in range(1, n + 1):
            if j >= 3 :
                nums.append(j)
        for n in nums:
            element = triangle[n - 2]
            len_s = len(element)
            vals = []
            for i in range(1, len_s):
                val = element[i] + element[i - 1]
                vals.append(val)
            tr = [1] + vals + [1]
            triangle.append(tr)
        if n == 0:
            return []
        if n == 1:
            return [[1]]
        if n == 2:
            return [[1],[1,1]]
        return triangle
