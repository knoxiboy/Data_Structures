package Data_Structures;

public class DS1_linkedlist{
    Node head = null;
    private int size;

    DS1_linkedlist(){
        this.size = 0;
    }

    class Node{
        String data;
        Node next;

        Node(String data){
            this.data = data;
            this.next = null;
            size++;
        }
    }

    public void addFirst(String data){
        Node newNode = new Node(data);
        if(head == null){
            head = newNode;
            return;
        }

        newNode.next = head;
        head = newNode;
    }

    public void addLast(String data){
        Node newNode = new Node(data);
        if(head == null){
            head = newNode;
            return;
        }

        Node currNode = head;
        while (currNode.next != null) {
            currNode = currNode.next;
        }

        currNode.next = newNode;
    }

    public void addInMiddle(int index, String data) {
        if(index > size || index < 0) {
            System.out.println("Invalid Index value");
            return;
        }
        size++;
    
    
        Node newNode = new Node(data);
        if(head == null || index == 0) {
            newNode.next = head;
            head = newNode;
            return;
        }
    
        Node currNode = head;
        for(int i = 1; i < size; i++) {
            if(i == index) {
                Node nextNode = currNode.next;
                currNode.next = newNode;
                newNode.next = nextNode;
                break;
            }
            currNode = currNode.next;
        }
    }

    public void getIndex(String data){
        if(head == null){
            System.out.println("Empty List");
            return;
        }

        int idx = 0;
        Node currNode = head;
        while (currNode != null) {     
            if(currNode.data == data) {
                System.out.println("Index of "+ data + " = " + idx);
            }
            currNode = currNode.next;
            idx++;
        }
    }

    public void printList(){
        if(head == null){
            System.out.println("Empty List");
            return;
        }

        Node currNode = head;
        while (currNode != null) {
            System.out.print(currNode.data + " -> ");
            currNode = currNode.next;
        }
        System.out.println("Null");
    }

    public void removeFirst(){
        if(head == null){
            System.out.println("Empty List");
            return;
        }

        size--;
        head = head.next;
    }

    public void removeLast(){
        if(head == null){
            System.out.println("Empty List");
            return;
        }

        size--;
        if(head.next == null){
            head = null;
            return;
        }

        Node lastNode = head.next;
        Node seclastNode = head;
        while(lastNode.next != null){
            lastNode = lastNode.next;
            seclastNode = seclastNode.next;
        }
        seclastNode.next = null; 
    }

    public int getSize(){
        return size;
    }

    public void reverseIterater(){
        if(head == null || head.next == null){
            return;
        }

        Node prevNode = head;
        Node currNode = head.next;
        while(currNode != null){
            Node nextNode = currNode.next;
            currNode.next = prevNode;

            prevNode = currNode;
            currNode = nextNode;
        }
        head.next = null;
        head = prevNode;
    }

    public Node reverseRecursive(Node head) {
        //empty node || last node or only one node
        if(head == null || head.next == null) {
            return head;
        }
 
 
        Node newHead = reverseRecursive(head.next);
       
        head.next.next = head;
        head.next = null;
        return newHead;
    }
 

    public static void main(String[] args) {
        DS1_linkedlist list = new DS1_linkedlist();
        list.addFirst("who");
        list.printList();
        list.addFirst("hi");
        list.printList();
        list.addLast("r");
        list.printList();
        list.addLast("u");
        list.printList();

        list.removeFirst();
        list.printList();
        list.removeLast();
        list.printList();
        list.removeFirst();
        list.printList();
        list.removeLast();
        list.printList();

        System.out.println("Size = " + list.getSize());

        list.addInMiddle(0, "1");
        list.addInMiddle(1, "5");
        list.addInMiddle(2, "7");
        list.addInMiddle(3, "3");
        list.addInMiddle(4, "8");
        list.addInMiddle(5, "2");

        list.printList();
        System.out.println("Size = " + list.getSize());

        list.getIndex("7");

        list.reverseIterater();
        list.printList();

        list.reverseRecursive(null);
        list.printList();
    }
}
