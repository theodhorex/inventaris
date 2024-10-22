from Node import Node

class Stack: #SLLNC dengan head
    def __init__(self):
        self._head=None
        self._size = 0

    def __len__(self):
        return self._size

    def isEmpty(self):
        return self._size == 0

    def push(self, e): #sama dengan tambah depan
        baru = Node(e, None)
        if self.isEmpty():
            self._head = baru
        else :
            baru._next = self._head
            self._head = baru
        self._size += 1

    def pop(self): #hapus depan
        if self.isEmpty():
            print("stack is empty")
            return None
        result = self._head._element  
        self._head = self._head._next  
        self._size -= 1  
        return result

    def printAll(self): #loop
        if self.isEmpty()==False:
            bantu = self._head
            while(bantu!=None):
                print(bantu._element," ",end='')
                bantu = bantu._next
                print()
        else:
            print("Kosong")
