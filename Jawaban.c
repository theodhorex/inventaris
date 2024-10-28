
class Queue:
    def __init__(self):
        self.data = []

    def enqueue(self, data):
        self.data.append(data)

    def dequeue(self):
        if len(self.data) == 0:
            return None
        return self.data.pop(0)

    def front(self):
        if len(self.data) == 0:
            return None
        return self.data[0]

    def get_length(self):
        return len(self.data)

    def write_all_data(self):
        print("DATA: ")
        for i in self.data:
            print(f"{i}")

def get_min(queue):
    if queue.get_length() == 0:
        return None

    # Initialize min_value to a high number
    min_value = float('inf')
    for item in queue.data:
        if item < min_value:
            min_value = item
    return min_value

def pop(queue):
    if queue.get_length() == 0:
        return None
    
    # Get the last element in the queue without modifying the queue
    last_index = queue.get_length() - 1
    last_item = queue.data[last_index]

    # Return the last item
    return last_item

if __name__ == "__main__":
    q = Queue()
    for i in [10, 8, 5, 60, 1, 2, 3, 4, 100]:
        q.enqueue(i)
    
    print("==========")
    print("Data Awal")
    q.write_all_data()
    print("==========")
    print(f"Data terkecil = {get_min(q)}")
    print("==========")
    print("Pembuktian isi queue tidak berubah")
    print("==========")
    print("Data Akhir")
    q.write_all_data()
    print("==========")
    print("============")
    print("Percobaan Pop")
    print(f"pop - 1 = {pop(q)}")
    print(f"pop - 2 = {pop(q)}")
    print(f"pop - 3 = {pop(q)}")
    print("=============")
    print("Hasil Akhir: ")
    q.write_all_data()
    print("=============")

