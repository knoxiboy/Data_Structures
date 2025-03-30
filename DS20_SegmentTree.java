package Data_Structures;

public class DS20_SegmentTree{
    static class Node{
        int data;
        int startIndex;
        int endIndex;
        Node left;
        Node right;

        Node(int data, int startIndex, int endIndex){
            this.data = data;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }
    }

    //creating a segment tree (for sum of a range of no's)
    public static Node create(Node root, int[] arr, int startIndex, int endIndex){
        if(startIndex > endIndex){
            return null;
        }

        if(startIndex == endIndex){
            return new Node(arr[startIndex], startIndex, endIndex);
        }

        int sum = 0;
        for(int i = startIndex; i <= endIndex; i++){
            sum += arr[i];
        }

        int mid = (startIndex+endIndex)/2;

        root = new Node(sum, startIndex, endIndex);

        //for left node
        root.left = create(root.left, arr, startIndex, mid);
        //for right node
        root.right = create(root.right, arr, mid + 1, endIndex);

        return root;
    }

    //for finding sum of a range of values
    public static int FindSum(Node root, int start, int end){
        //case 1 -> node completely inside the range
        if(start <= root.startIndex && end >= root.endIndex){
            return root.data;
        }
        //case 2 -> node completely outside the range
        else if(root.startIndex > end || root.endIndex < start){
            return 0;
        }
        else{
            return FindSum(root.left, start, end) + FindSum(root.right, start, end);
        }
    }

    public static void main(String[] args){
        //creating segment tree
        int[] arr = {12, 34, -21, 67, 59, 89, -47, 48};
        Node root = null;
        root = create(root, arr, 0, arr.length-1);
        
        //Find sum of a given range of values:
        //1. btw 2 and 5
        int sum1 = FindSum(root, 2, 5);
        System.out.println("Sum of values btw 2 and 5 = " + sum1);
        //2. btw 3 and 7
        int sum2 = FindSum(root, 3, 7);
        System.out.println("Sum of values btw 3 and 7 = " + sum2);
        //3. btw 1 and 4
        int sum3 = FindSum(root, 1, 4);
        System.out.println("Sum of values btw 1 and 4 = " + sum3);
    }
}