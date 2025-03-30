package Data_Structures;

public class DS18_Trie {
    static class Node {
        Node[] children = new Node[26];
        boolean eow;
 
        public Node() {
            for (int i=0; i<26; i++) {
                children[i] = null;
            }
        }
    }

    public static Node root = new Node();

    //insert
    public static void insert(String word) { //O(L)
        Node curr = root;
        for(int level = 0; level < word.length(); level++) {
            int idx = word.charAt(level) - 'a';
            if(curr.children[idx] == null) {
                curr.children[idx] = new Node();
            }
            curr = curr.children[idx];
        }
        curr.eow = true;
    }

    //search
    public static boolean search(String key) { //O(L)
        Node curr = root;
        for(int level = 0; level < key.length(); level++) {
            int idx = key.charAt(level)-'a';
            if(curr.children[idx] == null) {
                return false;
            }
            curr = curr.children[idx];
        }
        return curr.eow == true;
    }


    //Question 1 -> Word Break
    public static boolean wordBreak(String key) {
        int len = key.length();

        if(len == 0) {
            return true;
        }

        for(int i = 1; i <= len; i++) {
            String firstPart = key.substring(0, i);
            String secPart = key.substring(i);
            if(search(firstPart) && wordBreak(secPart)){
                return true;
            }
        }

        return false;
    }


    //Question 2 -> Starts With (similar to search just eow not requred)
    public static boolean startsWith(String prefix) {
        Node curr = root;
        for(int i = 0; i < prefix.length(); i++) {
            int idx = prefix.charAt(i)-'a';
            if(curr.children[idx] == null) {
                return false;
            }
            curr = curr.children[idx];
        }
        return true;
    }


    //Question 3 -> count unique substrings
    public static void buildTrie(String str){
        //insert all suffixes to Trie
        root = new Node();
        for(int i = 0; i < str.length(); i++) {
            insert(str.substring(i));
        }
    }

    public static int countNodes(Node root){ //no. of nodes = all prefixes possible 
        if(root == null) {
            return 0;
        }

        int count = 0;
        for(int i = 0; i < 26; i++) {
            if(root.children[i] != null) {
                count += countNodes(root.children[i]);
            }
        }

        return count+1; //extra one for the self node
    }
 

    //Question 4 -> longest word with all prefixes
    public static String ans = "";

    public static void longestWord(Node root, StringBuilder curr) {
        for(int i = 0; i < 26; i++){
            if(root.children[i] != null && root.children[i].eow == true) {
                curr.append((char)(i+'a'));
                if(curr.length() > ans.length()) {
                    ans = curr.toString();
                }
                longestWord(root.children[i], curr);
                curr.deleteCharAt(curr.length()-1);
            }
        }
    }
 

    public static void main(String args[]) {
        String words[] = {"the", "a", "there", "their", "any", "thee"};
        for (String word : words) {
            insert(word);
            System.out.println("inserted " + word);
        }

        System.out.println("thee -> " + search("thee"));
        System.out.println("thor -> " + search("thor"));

        //Question 1 -> Word Break
        /*
        String words2[] = {"i", "like", "sam", "samsung", "mobile"};
        String key = "ilikesamsung";

        for(int i = 0; i < words2.length; i++){
            insert(words2[i]);
        }

        System.out.println(wordBreak(key));
        */

        //Question 2 -> Starts With
        System.out.println(startsWith("the"));
        System.out.println(startsWith("thi"));

        //Question 3 -> count unique substrings
        String str = "apple";
        buildTrie(str);
        System.out.println(countNodes(root));

        //Question 4 -> longest word with all prefixes
        /*
        String words3[] = {"a", "bannana", "app", "ap", "apple", "appl", "apply"};
        for(int i = 0; i < words3.length; i++){
            insert(words3[i]);
        }

        longestWord(root, new StringBuilder(""));
        System.out.println(ans);
        */
    }
}