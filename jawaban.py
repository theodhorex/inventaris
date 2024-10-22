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



giman 
from SLLNCStack import Stack
import math
# Jika stack ganjil, maka dibulatkan ke bawah.
# contoh: ada stack dengan anggota 67. maka, fungsi ini akan menghapus 
# 33 elemen stack paling bawah (karena 67/2 = 33.5, dibulatkan ke bawah = 33).
def hapusSetengahBawah(stack: Stack) : 
    len_total = stack.getLen
    half_size = stack.getlen // 2
   
if __name__ == "__main__":
    s = Stack()
    # Isi Stack
    s.push(1)
    s.push(1.5)
    s.push(2)
    s.push(3)
    s.push(4)
    s.push(5)

    print("Tampilkan stack sebelum dihapus")
    s.printAll()
    print()

    print("Melakukan penghapusan Stack")
    hapusSetengahBawah(s)
    # Tampilkan data setelahnya
    print()

    print("Tampilkan stack setelah dihapus")
    s.printAll()

# COBA COPY PASTE KAN FILE README NYA SEMUA, TARO DIBAWAH TULISAN INI AJA
