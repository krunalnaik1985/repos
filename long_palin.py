def longest_palindrome(s1):
   len_s = len(s1)
   list1 = []
   dict1 = {}
   max_number = 0
   stack = {}
   for i in range(len_s):
      start = i
      for j in range(len_s):
         word = s1[start:j]
         if validPalindrome(word):
            word_len = len(word)
            max_number = max(word_len, max_number)
            if max_number not in stack:
               stack[max_number] = word
   print stack[max_number]

def validPalindrome(s):
   return s == s[::-1]


ab = "babad"
longest_palindrome(ab)
cd = "cbbd"
longest_palindrome(cd)
