[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/b7sdw6Q7)
# UTS2024
## KERJAKAN DI (***main.py***)  
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
3. untuk membuat fungsi bernama mergeStacksInterleave(stack1, stack2). **Fungsi ini akan menerima 2 buah stack dan menggabungkannya menjadi satu stack secara selang-seling. **  
**Contoh:**  
stack1: 1 -> 3 -> 5  
stack2: 2 -> 4 -> 6   
Stack gabungan: 1 -> 2 -> 3 -> 4 -> 5 -> 6.
Fungsi mergeStacksInterleave() akan mereturn stack gabungan. 

## TEST CASE
```
if __name__ == "__main__":
    s1 = Stack()
    # Isi Stack 1
    s1.push(1)
    s1.push(3)
    s1.push(5)

    s2 = Stack()
    # Isi Stack 2
    s2.push(2)
    s2.push(4)
    s2.push(6)

    s3 = mergeStacksInterleave(s1,s2)
    s3.printAll()

```

## POINT PENILAIAN TOTAL 100:
1. Fungsi push tepat : **30 poin**
2. Fungsi pop tepat : **30 poin**
3. Fungsi mergeStacksInterleave tepat : **40 poin**

