package Data_Structures;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class DS17_HashingProblems{
    public static void main(String args[]){
        //Ques1-> majority elements in an array (occur more than n/3 times)
        HashMap<Integer, Integer> map = new HashMap<>();
        int nums[] = {1, 3, 2, 5, 1, 3, 1, 5, 1};

        for(int i = 0; i < 9; i++){
            if(map.containsKey(nums[i])){
                map.put(nums[i], map.get(nums[i])+1);
            }
            else{
                map.put(nums[i], 1);
            }
        }

        for(Integer key : map.keySet()){
            if(map.get(key) > 3){
                System.out.print(key + " ");
            }
        }
        System.out.println();


        //Ques2 -> Union of 2 arrays
        HashSet<Integer> set = new HashSet<>();
        int n = 4, m = 6;
        int array1[] = new int[n];
        int array2[] = new int[m];

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter elements of array 1: ");
        for(int i = 0; i < n; i++){
            array1[i] = sc.nextInt();
        }

        System.out.println("Enter elements of array 2: ");
        for(int i = 0; i < m; i++){
            array2[i] = sc.nextInt();
        }

        for(int i = 0; i < n; i++){
            set.add(array1[i]);
        }

        for(int i = 0; i < m; i++){
            set.add(array2[i]);
        }

        System.out.print("Union of two arrays = ");
        System.out.println(set);
        

        //Ques3 -> Intersection of two arrays
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();

        for(int i = 0; i < n; i++){
            set1.add(array1[i]);
        }

        for(int i = 0; i < m; i++){
            if(set1.contains(array2[i])){
                set2.add(array2[i]);
                set1.remove(array2[i]);
            }
        }

        System.out.print("Intersection of two arrays = ");
        System.out.println(set2);


        //Ques4 -> find itinerary from ticket
        HashMap<String, String> ticket = new HashMap<>();
        ticket.put("Chennai", "Bengaluru");
        ticket.put("Mumbai", "Delhi");
        ticket.put("Goa", "Chennai");
        ticket.put("Delhi", "Goa");

        HashMap<String, String> revticket = new HashMap<>();
        for(String key: ticket.keySet()){
            revticket.put(ticket.get(key), key);
        }
        
        String start = null;
        for(String key: ticket.keySet()){
            if(!revticket.containsKey(key)){
                start = key;
            }
        }

        while(ticket.containsKey(start)){
            System.out.print(start + " -> ");
            start = ticket.get(start);
        }
        System.out.println(start);


        //Ques5 -> subarray sum eqaul to k
        int arr[] = {10, 2, -2, 20, 10};
        int k = -10;
        HashMap<Integer, Integer> Map = new HashMap<>();
        
        map.put(0, 1);
        int ans = 0, sum = 0;

        for(int i = 0; i < arr.length; i++){
            sum += arr[i];

            if(Map.containsKey(sum-k)){
                ans += map.get(sum-k);
            }

            if(Map.containsKey(sum)){
                Map.put(sum, Map.get(sum)+1);
            }
            else{
                Map.put(sum, 1);
            }
        }

        System.out.println("No. of subarrays sum equal to k : " + ans);
    }
}
