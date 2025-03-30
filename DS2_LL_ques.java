package Data_Structures;

import java.sql.Time;
import java.util.List;

public class DS2_LL_ques {
    Node head = null;
    private int size;

    DS2_LL_ques(){
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

    /*
    QUES 1 -->
    Find the nth node from the end & remove it.
    Time complexity - O(n)
    Space complexity - O(1)
    */
    public Node removeNthFromEnd(Node head, int n) {
    if(head.next == null) {
        return null;
    }
    
    
    int size = 0;
    Node temp = head;
    while(temp != null) {
        temp = temp.next;
        size++;
    }
    
    //removing SIZEth node from last i.e. head
    if(n == size) {
        return head.next;
    }
    
    //find previous node
    int ptf = size - n; // position to find
    Node prev = head; // previous node
    int cp = 1; // current position
    
    while(cp != ptf) {
        prev = prev.next;
        cp++;
    }
    
    prev.next = prev.next.next;
    return head;

    }

    /*
    QUES 2 -->
    Check if a Linked List is a palindrome
    Time complexity - O(n)
    Space complexity - O(1)
    */

    public Node getMiddle(Node head) {
    Node fast = head;
    Node slow = head;
    while (fast.next != null && fast.next.next != null) {
        fast = fast.next.next;
        slow = slow.next;
    }
    return slow;
    }


    public Node reverse(Node head) {
    Node prev = null;
    Node curr = head;
    
    while (curr != null) {
        Node next = curr.next;
        curr.next = prev;
        prev = curr;
        curr = next;
    }
    return prev;
    }




    public boolean isPalindrome(Node head) {
    if(head == null || head.next == null) {
        return true;
    }
    
    Node firstHalfEnd = getMiddle(head);
    Node secondHalfStart = reverse(firstHalfEnd.next);
    Node firstHalfStart = head;
    
    while(secondHalfStart != null) {
        if(secondHalfStart.data != firstHalfStart.data) {
            return false;
        }
        secondHalfStart = secondHalfStart.next;
        firstHalfStart = firstHalfStart.next;
    }
    
    return true;
    }
    /*
    QUES 3 -->
    Detecting Loop in a Linked List.
    Time complexity - O(n)
    Space complexity - O(1)
    */

    public boolean hasCycle(Node head) {
    Node slow = head;
    Node fast = head;
    
    while(fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        
        if(fast == slow) {
            return true;
        }
    }
    
    return false;
    }



    public static void main(String[] args) {
        DS1_linkedlist list = new DS1_linkedlist();

    }
}