from SLLNCStack import Stack
import math

def mergeStacksInterleave(stack1: Stack, stack2: Stack) -> Stack:
    merged_stack = Stack()

    while not stack1.isEmpty() or not stack2.isEmpty():
        if not stack2.isEmpty():
            merged_stack.push(stack2.pop()) 
        if not stack1.isEmpty():
            merged_stack.push(stack1.pop()) 

    return merged_stack
    
if __name__ == "__main__":
    s1 = Stack()
    # Isi Stack 1
    s1.push(1)
    s1.push(3)
    s1.push(5)

    s2 = Stack()
    # Isi Stack 2
    beda
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

    s2.push(2)
    s2.push(4)
    s2.push(6)

    s3 = mergeStacksInterleave(s1,s2)
    s3.printAll()
    
