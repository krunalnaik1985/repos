class Solution(object):
    #  @param s, a list of 1 length strings, e.g., s = ['h','e','l','l','o']
    #  @return nothing
    def reverse1(self, start, end, s1):
        #  import pdb;pdb.set_trace()
        end1 = end
        while end > 0 and start < end :
            start1 = s1[start]
            end1 = s1[end - 1]
            s1[end - 1] = start1
            s1[start] = end1
            end = end - 1
            start = start + 1
        return s1
    def reverseWords(self, s):
        len_s = len(s)
        start = 0
        for i in range(len_s):
            word = s[i]
            if word == ' ':
                s = self.reverse1(start, i, s)
                start = i + 1
        if start < len_s:
           s = self.reverse1(start, len_s , s)
        s = self.reverse1(0, len_s, s)
