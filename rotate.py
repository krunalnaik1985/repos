class solution(object):
   def __init__(self, matrix):
      """ matrix"""
      self.matrix = matrix

   def validate(self):
      """check it is N*N matrix"""
      row_len = len(self.matrix)
      valid = True
      for i in range(row_len):
         if len(self.matrix[i]) != row_len:
            valid = False
      return valid

   def rotate(self):
      """ rotate image inplace"""
      if not self.validate():
         raise Exception('N*N Matrix should have same N ')
      row_len = len(self.matrix)
      col_len = len(self.matrix[0])
      row_end = row_len - 1
      row_start = 0
      while row_start < row_end:
         temp = self.matrix[row_start]
         self.matrix[row_start] = self.matrix[row_end]
         self.matrix[row_end] = temp
         row_start = row_start + 1
         row_end = row_end - 1
      for i in range(row_len):
         k = i + 1
         for j in range(k, col_len):
            temp1 = self.matrix[i][j]
            self.matrix[i][j] = self.matrix[j][i]
            self.matrix[j][i] = temp1

image = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
solution_obj = solution(image)
solution_obj.rotate()
print solution_obj.matrix
