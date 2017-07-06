
class solution(object):
   def __init__(self, grid, word_list):
      """Default Method for Initialization
         grid -> pass 2D Array
         word_list -> word list with words"""
      self.grid = grid
      self.words = word_list
      #  list of valid moves
      self.valid_moves = [(2, -1), (-1, 2), (1, 2), (-2, -1), (-1, -2), (1, -2), (-2, 1), (2, 1)]
      self.max_len = self.get_max_len()
      self.output_list = []
      self.max_len
      self.get_max_word(self.words)

   def issafe(self, row, col):
      """ this method determines that it is safe to move next knight move
         return type -> boolean"""
      len_row = len(self.grid)
      len_col = len(self.grid[0])
      if row > -1 and col > -1 and row < len_row and col < len_col:
         return True
      return False

   def get_max_len(self):
      """ it should go till this range for verification"""
      len_s = None
      for each_word in self.words:
         if len_s is None:
            len_s = len(each_word)
         else:
            if len(each_word) > len_s:
               len_s = len(each_word)
      return len_s

   def get_max_word(self, words):
      """ for output results based on length"""
      len_s = None
      max_word = None
      for each_word in words:
         if len_s is None:
            len_s = len(each_word)
            max_word = each_word
         else:
            if len(each_word) > len_s:
               len_s = len(each_word)
               max_word = each_word
      return max_word

   def get_next_cord(self, x, y):
      """it gives next cordinates based on self.valid_moves"""
      next = []
      for valid in self.valid_moves:
         next_cordi = (x + valid[1], y + valid[0])
         if next_cordi[0] < len(self.grid) and next_cordi[1] < len(self.grid):
            next.append(next_cordi)
      return next

   def check_word(self, word):
      """ verifies that word exists in list"""
      if word in self.words:
         return True
      return False

   def word_exists(self, word):
      """ quick validation
          it uses quick validation method
          for word fortran->
          if word is "for" go for next move
          if word is "fox" dont go for next move
          it helps run time and reduces time complexity"""
      end = 3
      w1 = word[0:end]
      for each_word in self.words:
         w2 = each_word[0:end]
         if w1 == w2:
            return True
      return False

   def make_move(self, row, col, word):
      """recursion based word verfication"""
      #  dont go beyond max limit
      if len(word) > self.max_len + 1:
         return False
      #  reduces time complexity and quick validation
      if len(word) > 4:
         if not self.word_exists(word.lower()):
            return False
      #  if word exists then tell recursion to exit
      if self.check_word(word.lower()):
         return True
      #  if it is beyond limit then dont do further recursion
      if col >= len(self.grid) or row >= len(self.grid) or col < 0 or row < 0:
         return False
      if self.issafe(row, col):
         word = '%s%s' % (word, self.grid[row][col])
         move_cord = self.get_next_cord(row, col)
         for each_move in move_cord:
            move_row, move_col = each_move[0], each_move[1]
            #  do recursion
            if self.make_move(move_row, move_col, word) == True:
               #  if it is True then Add to list
               self.output_list.append(word)
               return word

   def moves(self):
      len_row = len(self.grid)
      len_col = len(self.grid[0])
      word = ''
      #  for all row and columns
      for i in range(len_row):
         for j in range(len_col):
            self.make_move(i, j, word)
      #  if word is found then print it
      print "Max Word is:%s" % self.get_max_word(self.output_list)

grid = [['Q', 'W' , 'E', 'R', 'T', 'N' , 'U', 'I'],
        ['O', 'P', 'A', 'A', 'D', 'F', 'G', 'H'],
        ['T', 'K', 'L', 'Z', 'X', 'C', 'V', 'B'],
        ['N', 'M', 'R', 'W', 'F', 'R', 'T', 'Y'],
        ['U' , 'I', 'O', 'P', 'A', 'S', 'D', 'F'],
        ['G' , 'H' , 'J', 'O', 'L', 'Z', 'X', 'C'],
        ['V', 'B', 'N', 'M', 'Q', 'W', 'E', 'R'],
        ['T', 'Y', 'U', 'I', 'O', 'P', 'A', 'S']]
word_list = ['algol', 'fortran', 'simula']
b = solution(grid, word_list)
b.moves()
