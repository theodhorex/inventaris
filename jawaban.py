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







DAH INI AJALAH YANG KAU COPY PASTE KAN DI FILE MAIN.PY NYA
OHYA, COBA TULISKAN DULU DI FOLDER MU ADA FILE APA AJA ISI NYA?
TULISKAN DIBAWAH INI

from SLLNCStack import Stack
import math
# Jika stack ganjil, maka dibulatkan ke bawah.
# contoh: ada stack dengan anggota 67. maka, fungsi ini akan menghapus 
# 33 elemen stack paling bawah (karena 67/2 = 33.5, dibulatkan ke bawah = 33).
def hapusSetengahBawah(stack: Stack) : 
    len_total = stack.getLen()
    half_size = stack.getLen() // 2
    temp_stack = Stack()
    for _ in range(half_size, len_total):
        temp_stack.push(stack.pop())
    for _ in range(half_size):
        stack.pop()
    while temp_stack.getLen() > 0:
        stack.push(temp_stack.pop())
        
   
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
Anda diberikan sebuah class Stack dengan methode sebagai berikut:
- __len__():    
  method ini mengembalikan banyak elemen pada Stack  
- isEmpty () :    
  method untuk mengetahui apakah stack kosong atau tidak.
- printAll():    
  method ini akan menampilkan semua data yang ada pada stack mulai dari data yang paling baru masuk sampai yang paling lama berada di stack  

Tugas Anda sekarang adalah :
1. Membuat method push() data ke stack didalam kelas SLLNCStack. 
2. Membuat method pop() data dari stack didalam kelas SLLNCStack. 
3. untuk membuat fungsi bernama hapusSetengahBawah(stack). **Fungsi ini akan menerima parameter berupa stack dan akan menghapus setengah anggota paling bawah pada stack tersebut. **
Ingat bahwa fungsi ini hanyalah fungsi biasa dan bukanlah sebuah method. Jika stack ganjil, maka dibulatkan ke bawah.  
contoh: ada stack dengan anggota 67. maka, fungsi ini akan menghapus 
33 elemen stack paling bawah (karena 67/2 = 33.5, dibulatkan ke bawah = 33).  
setelah stack dimasukkan ke dalam fungsi ini, stack tersebut **HARUS** berubah isinya (fungsi ini tidak me-return apapun). 

## TEST CASE
```
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
    s.printData()
    print()

    print("Melakukan penghapusan Stack")
    hapusSetengahBawah(s)
    # Tampilkan data setelahnya
    print()

    print("Tampilkan stack setelah dihapus")
    s.printData()
