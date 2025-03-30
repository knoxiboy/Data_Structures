package Data_Structures;

public class DS12_BST {
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

    //searching an element
    public static void search(Node root, int key){
        if(root == null){
            System.out.println("Not Found");
            return;
        }

        if(root.data == key){
            System.out.println("Found");
            return;
        }

        if(root.data < key){
            search(root.right, key);
        }
        else{
            search(root.left, key);
        }
    }

    //delete a node
    public static Node delete(Node root, int val) {
        if(root.data < val){
            root.right = delete(root.right, val);
        }
        else if(root.data > val){
            root.left = delete(root.left, val);
        }
        else{ //root.data == val

            //case 1
            if(root.left == null && root.right == null){
                return null;
            }

            //case 2
            if(root.left == null){
                return root.right;
            }
            else if(root.right == null){
                return root.left;
            }

            //case 3
            Node IS = inorderSuccessor(root.right);
            root.data = IS.data;
            root.right = delete(root.right, IS.data);
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
        //creating a Binary Search Tree
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

        //searching an element
        int key = 6;
        System.out.println("Searching for " + key + " : ");
        search(root, key);

        //delete a node
        int val = 4;
        delete(root, val);
        System.out.println("After deleting Node" + val + " :");
        inorder(root);
        System.out.println();

    }
}
