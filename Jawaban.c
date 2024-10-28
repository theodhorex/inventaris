bilang di wa kolo masih sala
class Queue:
    def __init__(self):
        self.data = []

    def enqueue(self, data):
        self.data.append(data)

    def is_empty(self):
        return len(self.data) == 0

    def dequeue(self):
        if self.is_empty():
            return None
        return self.data.pop(0)

    def front(self):
        if self.is_empty():
            return None
        return self.data[0]

    def write_all_data(self):
        print("Data dalam queue: ")
        for i, data in enumerate(self.data):
            print(f"{(i + 1)}. {data}")

    def get_length(self):
        return len(self.data)


def serobot_antrian(q: Queue, data):
    # Insert the new data at the front of the queue
    q.data.insert(0, data)  # This modifies the queue directly.


def get_max(q: Queue):
    # Find the maximum value in the queue
    if q.is_empty():
        return None
    max_value = q.data[0]
    for item in q.data:
        if item > max_value:
            max_value = item
    return max_value


def main():
    q = Queue()
    for i in [1, 3, 2, 4, 2, 3, 6, 7]:
        q.enqueue(i)
    print("=== Antrian Awal ===")
    q.write_all_data()
    print("=== Melakukan Penyerobotan Antrian === ")
    serobot_antrian(q, 20)
    serobot_antrian(q, 20)
    serobot_antrian(q, 40)
    serobot_antrian(q, 30)
    print("Antrian sesudah di serobot: ")
    q.write_all_data()
    print("Melakukan pencarian data terbesar: ")
    print(f"Data terbesar: {get_max(q)} ")
    print("Pembuktian bahwa data tidak berubah: ")
    q.write_all_data()


if __name__ == "__main__":
    main()
