def hapusSetengahBawah(stack): 
    len_total = stack.getLen()
    half_size = stack.getLen() // 2
    temp_stack = Stack()
    for _ in range(half_size, len_total):
        temp_stack.push(stack.pop())
    for _ in range(half_size):
        stack.pop()
    while temp_stack.getLen() > 0:
        stack.push(temp_stack.pop())
