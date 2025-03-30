package Data_Structures;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class DS13_BST_Ques {
    static class Node{
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data = data;
        }
    }

    //inserting in Binary Search Tree
    public static Node insert(Node root, int val){
        if(root == null){
            root = new Node(val);
            return root;
        }

        if(root.data > val){
            //left subtree
            root.left = insert(root.left, val);
        }
        else{
            //right subtree
            root.right = insert(root.right, val);
        }

        return root;
    }

    //inorder traversal
    public static void inorder(Node root){
        if(root == null){
            return;
        }
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    //Ques 1 -> Print in range (x to y)
    public static void printInRnage(Node root, int x, int y){
        if(root == null){
            return;
        }

        if(root.data >= x && root.data <= y){
            printInRnage(root.left, x, y);
            System.out.print(root.data + " ");
            printInRnage(root.right, x, y);
        }
        else if(root.data <= x){
            printInRnage(root.right, x, y);
        }
        else if(root.data >= y){
            printInRnage(root.left, x, y);
        }

    }

    //ques 2 -> Print root to leaf
    public static void printRootToLeaf(Node root, ArrayList<Integer> path){
        if(root == null){
            return;
        }

        path.add(root.data);

        //leaf
        if(root.left == null && root.right == null){
            printPath(path);
        }
        else{
            printRootToLeaf(root.left, path);
            printRootToLeaf(root.right, path);
        }
        path.remove(path.size()-1);
    }

    public static void printPath(ArrayList<Integer> path){
        for(int i = 0; i < path.size(); i++){
            System.out.print(path.get(i) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        //creating a Binary Search Tree
        int values[] = {5, 1, 3, 4, 2, 7};
        Node root = null;

        for(int i = 0; i < values.length; i++){
            root = insert(root, values[i]);
        }

        //inorder traversal
        System.out.println("In-order :");
        inorder(root);
        System.out.println();

        //Ques 1 -> Print in range (x to y)
        System.out.println("Nodes in range of 2 to 6 : ");
        printInRnage(root, 2, 6);
        System.out.println();

        //ques 2 -> Print root to leaf
        System.out.println("Paths : ");
        printRootToLeaf(root, new ArrayList<>());

    }
}
