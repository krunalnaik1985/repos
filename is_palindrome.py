a1 = 'abcba'
a2 = 'racecar'


def valid_palindrom(a1):
    len_a = len(a1) - 1
    middle = (len_a - 0) / 2
    i = 0
    while i <= middle and len_a >= middle:
        if not a1[i] == a1[len_a]:
            return False
        i = i + 1
        len_a = len_a - 1
    return True


print valid_palindrom(a1)
print valid_palindrom(a2)
