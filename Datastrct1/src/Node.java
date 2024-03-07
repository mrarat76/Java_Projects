class Node<T> {
    T data;
    Node<T> next;

    // Düğüm oluşturucu
    public Node(T data) {
        this.data = data;
        this.next = null;
    }
}

// LinkedList sınıfı, bağlı listeyi yönetir
class LinkedList<T> {
    Node<T> head; // Bağlı listenin başı

    // Bağlı listeye eleman ekleme (başa ekleme)
    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);

        // Yeni düğümü listenin başına ekler
        newNode.next = head;
        head = newNode;
    }

    // Bağlı listeye eleman ekleme (sona ekleme)
    public void addLast(T data) {
        Node<T> newNode = new Node<>(data);

        // Eğer liste boşsa, yeni düğümü başa ekler
        if (head == null) {
            head = newNode;
            return;
        }

        // Son düğümü bulur ve yeni düğümü sona ekler
        Node<T> temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    public void InsertAfter (T data, T search){
        Node <T> temp=head;
        Node <T> newNode= new Node<>(data);
        while (temp!= null && !temp.data.equals(search)){
            temp= temp.next;

        } if (temp!= null){
            newNode.next= temp.next;
            temp.next=newNode;
        }
    }

    boolean remove (T data){
        if (head== null){
            System.out.println("Empty list!");
        } else{
            if(head.data.equals(data)){
                head=head.next;
                return true;
            } else{
                Node <T> temp = head.next;
                Node <T> prev =head;
                while (temp!= null && !temp.data.equals(data)){
                    prev= temp;
                    temp = temp.next;
                }

                if(temp != null){
                    prev.next= temp.next;
                            return true;

                } else {
                    System.out.println(data + "Not found!");

                }
            }

        }

        return false;
    }

    // Bağlı listeyi yazdırma
    public void printList() {
        Node<T> temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public void size(){
        int count =0;
        Node <T> temp = head;
        while ( temp != null){
            count++;

            temp= temp.next;

        }
        System.out.println("Count"+ count);
    }
    public static void main(String[] args) {
        LinkedList<Integer> liste = new LinkedList<>();

    }



}

class ListNode<M> {
    M data;
    ListNode<M> next;

    ListNode(M data) {
        this.data = data;
        this.next = null;
    }
}


class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null){
            return null;
        }

        ListNode current = head;

        while (current.next != null) {
            if (current.data == current.next.data) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }

        return head;
    }
}





