package Data_Structures;

import java.util.Scanner;

public class DS19_AVL_Trees {
    static class Node{
        int data;
        Node left;
        Node right;
        int height;

        Node(int data){
            this.data = data;
            height = 1;
        }
    }

    // Get the height of the node
    public static int height(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    // Get maximum of two integers
    public static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // Right rotate subtree rooted with y
    public static Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    // Left rotate subtree rooted with x
    public static Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    // Get balanceFactor factor of node N
    public static int getBalance(Node N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    //inserting in AVL Tree
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

        // Update height of this ancestor node
        root.height = 1 + max(height(root.left), height(root.right));

        // Get the balanceFactor factor of this ancestor node
      	// to check whether this node became unbalanced
        int balanceFactor = getBalance(root);

        // Left left case
        if (balanceFactor > 1 && val < root.left.data)
            return rightRotate(root);

        // Right Right Case
        if (balanceFactor < -1 && val > root.right.data)
            return leftRotate(root);

        // Left Right Case
        if (balanceFactor > 1 && val > root.left.data) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Left Case
        if (balanceFactor < -1 && val < root.right.data) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    //inorder traversal -> left, root, right
    public static void inorder(Node root){
        if(root == null){
            return;
        }
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    //preorder traversal -> root, left, right
    public static void preorder(Node root){
        if(root == null){
            return;
        }

        System.out.print(root.data + " ");
        preorder(root.left);
        preorder(root.right);
    }

    //postorder traversal -> left, right, root
    public static void postorder(Node root){
        if(root == null){
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.data + " ");
    }

    //searching an element in AVL Tree
    public static void search(Node root, int val){
        if(root == null){
            System.out.println("Not Found");
            return;
        }

        if(root.data == val){
            System.out.println("Found");
            return;
        }

        if(root.data < val){
            search(root.right, val);
        }
        else{
            search(root.left, val);
        }
    }

    //delete a node in AVL Tree
    public static Node delete(Node root, int val) {
        if(root.data < val){
            root.right = delete(root.right, val);
        }
        else if(root.data > val){
            root.left = delete(root.left, val);
        }
        else{ //root.data == val

            //case 1 -> No child case
            if(root.left == null && root.right == null){
                return null;
            }

            //case 2 ->  One child case
            if(root.left == null){
                return root.right;
            }
            else if(root.right == null){
                return root.left;
            }

            //case 3 -> node with two children
            Node IS = inorderSuccessor(root.right);
            root.data = IS.data;
            root.right = delete(root.right, IS.data);
        }

        root.height = Math.max(height(root.left),height(root.right)) + 1;

        int balanceFactor = getBalance(root);

        // Left Left Case
        if (balanceFactor > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        // Left Right Case
        if (balanceFactor > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Right Case
        if (balanceFactor < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        // Right Left Case
        if (balanceFactor < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    public static Node inorderSuccessor(Node root){
        while(root.left != null){
            root = root.left;
        }
        return root;
    }

    public static void main(String[] args) {
        //creating a AVL Tree
        int values[] = {5, 1, 3, 4, 2, 7};
        Node root = null;

        for(int i = 0; i < values.length; i++){
            root = insert(root, values[i]);
        }

        //preorder traversal
        System.out.println("Pre-order :");
        preorder(root);
        System.out.println();

        //inorder traversal
        System.out.println("In-order :");
        inorder(root);
        System.out.println();

        //postorder traversal
        System.out.println("Post-order :");
        postorder(root);
        System.out.println();

        //insert any value
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a value to insert: ");
        int n = sc.nextInt();
        insert(root, n);

        //inorder traversal
        System.out.println("In-order :");
        inorder(root);
        System.out.println();

        //searching an element
        int val = 6;
        System.out.println("Searching for " + val + " : ");
        search(root, val);

        //delete a node
        int val2 = 4;
        delete(root, val2);
        System.out.println("After deleting Node " + val2 + " :");
        inorder(root);
        System.out.println();

    }
}
