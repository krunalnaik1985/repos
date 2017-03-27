def encrypt(s1):
   len_s = len(s1)
   word_2 = None
   count = 1
   index = None
   dic2 = {}
   for i in range(len_s):
      word = s1[i]
      if word_2 is None:
         word_2 = word
      if word == word_2:
         count = count + 1
      else:
         word_2 = None
         count = 1
         index = None
      if index is None:
         index = i
      dic2[word] = (count, index)
   text = ''
   for i in range(len_s):
      #  import pdb;pdb.set_trace()
      if i == dic2[s1[i]][1]:
         text = text + '%s%s' % (s1[i], dic2[s1[i]][0])
   print text
ab = 'aaaabbbcccdef'
encrypt(ab)
