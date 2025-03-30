package Data_Structures;

import javax.swing.tree.TreeNode;

public class DS11_BinaryTreeQues {
    static class Node{
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static class BinaryTree{
        static int idx;

        public static Node buildTree(int nodes[]){
            idx = -1;
            return buildTreeHelper(nodes);
        }

        public static Node buildTreeHelper(int nodes[]){
            idx++;
            if(nodes[idx] == -1 || idx >= nodes.length){
                return null;
            }

            Node newNode = new Node(nodes[idx]);
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);

            return newNode;
        }
    }

    //Ques.1 -> Count the no. of nodes 
    public static int CountOfNodes(Node root) {
        if(root == null) {
            return 0;
        }

        int leftNodes = CountOfNodes(root.left);
        int rightNodes = CountOfNodes(root.right);
        return leftNodes + rightNodes + 1;
    }

    //Ques.2 -> Sum of nodes 
    public static int SumOfNodes(Node root) {
        if(root == null) {
            return 0;
        }

        int leftSum = SumOfNodes(root.left);
        int rightSum = SumOfNodes(root.right);
        return leftSum + rightSum + root.data;
    }

    //Ques.3 -> Height of tree
    public static int HeightOfTree(Node root) {
        if(root == null) {
            return 0;
        }
 
        int leftHeight = HeightOfTree(root.left);
        int rightHeight = HeightOfTree(root.right);

        int myHeight = Math.max(leftHeight, rightHeight) + 1;
        return myHeight;
    }

    //Ques.4 -> Diameter of tree [O(N^2)]
    public static int DiameterOfTree(Node root){
        if(root == null){
            return 0;
        }

        int D1 = DiameterOfTree(root.left);
        int D2 = DiameterOfTree(root.right);
        int D3 = HeightOfTree(root.left) + HeightOfTree(root.right) + 1;

        return Math.max(D3, Math.max(D2, D1));
    }

    //Ques.4 -> Diameter of tree [O(N)]
    static class TreeInfo{
        int ht;
        int diam;

        TreeInfo(int h, int d){
            this.ht = h;
            this.diam = d;
        }
    }

    public static TreeInfo diameter(Node root) {
        if(root == null) {
            return new TreeInfo(0, 0);
        }
 
        TreeInfo leftTI = diameter(root.left);
        TreeInfo rightTI = diameter(root.right);
 
        int myHeight = Math.max(leftTI.ht, rightTI.ht) + 1;

        int diam1 = leftTI.ht + rightTI.ht + 1;
        int diam2 = leftTI.diam;
        int diam3 = rightTI.diam;
 
        int myDiam = Math.max(diam1, Math.max(diam2, diam3));
 
        return new TreeInfo(myHeight, myDiam);
    }
 
        //Ques.5 -> Subtree of another tree 
        public boolean isIdentical(Node root, Node subRoot){
            if(subRoot == null && root == null){
                return true;
            }
            if(root == null || subRoot == null){
                return false;
            }
            if(root.data == subRoot.data){
                return isIdentical(root.left, subRoot.left) && isIdentical(root.right, subRoot.right);
            }
            return false;
        }
        
        public boolean isSubtree(Node root, Node subRoot) {
            if(subRoot == null){
                return true;
            }
            if(root == null){
                return false;
            }
            if(isIdentical(root, subRoot)){
                return true;
            }
            return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
        }
    

    public static void main(String[] args) {
        int nodes[] = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};
        BinaryTree tree = new BinaryTree();
        Node root = tree.buildTree(nodes);

        //count of nodes
        int count = CountOfNodes(root);
        System.out.println("No. of nodes = " + count);

        //sum of nodes
        int sum = SumOfNodes(root);
        System.out.println("Sum of nodes = " + sum);

        //Height of tree
        int h = HeightOfTree(root);
        System.out.println("Height of tree = " + h);

        //Diameter of tree
        int d = DiameterOfTree(root);
        System.out.println("Diameter of tree = " + d);

        int d2 = diameter(root).diam;
        System.out.println("Diameter of tree = " + d2);

        //Subtree of another tree
        int subNodes[] = {2, 4, -1, -1, 5, -1, -1};
        BinaryTree SubTree = new BinaryTree();
        Node subRoot = SubTree.buildTree(subNodes);
        System.out.println();
    }
}
