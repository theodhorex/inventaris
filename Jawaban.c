
class Queue :
    def __init__(self):
        self.data = []
    
    def enqueue(self, data) :
        self.data.append(data)

    def dequeue(self):
        if len(self.data) == 0:
            return
        else :
            return self.data.pop(0)

    def front(self):
        if len(self.data) == 0 :
            return 
        else :
            return self.data[0]
    
    def get_length(self) :
        return len(self.data)

    def write_all_data(self):
        print("DATA: ")
        for i, data in enumerate(self.data):
            print(f"{data}")



# Haram untuk menggunakan list
def get_min(queue):
    if queue.get_length() == 0:
        return None
    nilai_min = float('inf')
    for nilai_keluar in queue.data:
        if nilai_keluar < nilai_min:
            nilai_min = nilai_keluar
    return nilai_min
    
def pop(queue):
    if queue.get_length() == 0:
        return None
    length = queue.get_length()
    for _ in range(length - 1):  
        queue.enqueue(queue.dequeue())
        
    last_element = queue.dequeue()  
    return last_element
    
     

if __name__ == "__main__":
    q : Queue = Queue()
    for i in [10, 8, 5, 60,1,2,3,4,100]:
        q.enqueue(i)
    print("==========")
    print("Data Awal")
    q.write_all_data()
    print("==========")
    print(f"Data terkecil = {get_min(q)}")
    print("==========")
    print()
    print("Pembuktian isi queue tidak berubah")
    print("==========")
    print("Data Akhir")
    q.write_all_data()
    print("==========")
    print()
    print("============")
    print("Percobaan Pop")
    print(f"pop - 1 = {pop(q)}")
    print(f"pop - 2 = {pop(q)}")
    print(f"pop - 3 = {pop(q)}")
    print("=============")
    print("Hasil Akhir: ")
    q.write_all_data()
    print("=============")













length = queue.get_length()
    for _ in range(length - 1):  # Pindahkan semua elemen kecuali yang terakhir
        queue.enqueue(queue.dequeue())
    
    last_element = queue.dequeue()  # Ambil elemen terakhir
    return last_element
