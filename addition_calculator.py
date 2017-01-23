
a1 = [6,3,4,5]
b1 = [9,4,5,9]
def perform_sum(a1,b1):
    a_len = len(a1) - 1
    b_len = len(b1) - 1
    carry = 0
    final_ar = []
    while a_len >= 0 or b_len >= 0:
        first = a1[a_len] if a_len >= 0 else 0
        second = b1[b_len] if b_len >= 0 else 0
        sum = first + second + carry
        final_ar.append(sum % 10)
        carry = sum / 10
        a_len = a_len - 1
        b_len = b_len - 1
    if carry:
        #import pdb;pdb.set_trace()
        print carry
        final_ar.append(carry)
    return final_ar

print perform_sum(a1,b1)[::-1]
