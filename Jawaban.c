
length = queue.get_length()
    for _ in range(length - 1):  # Pindahkan semua elemen kecuali yang terakhir
        queue.enqueue(queue.dequeue())
    
    last_element = queue.dequeue()  # Ambil elemen terakhir
    return last_element
