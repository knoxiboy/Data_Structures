package Data_Structures;

import java.util.ArrayList;

//implementation of max heap
class MaxHeap<T extends Comparable<T>> {
    private ArrayList<T> List = new ArrayList<>();

    private void swap(int first, int sec){
        T temp = List.get(first);
        List.set(first, List.get(sec));
        List.set(sec, temp);
    }

    private int parent(int idx){
        return (idx-1)/2;
    }

    private int left(int idx){
        return (idx*2)+1;
    }

    private int right(int idx){
        return (idx*2)+2;
    }

    //inserting a value
    public void insert(T val){
        List.add(val);
        upheap(List.size() - 1);
    }

    private void upheap(int idx){
        if(idx == 0){
            return;
        }

        int p = parent(idx);

        if(List.get(idx).compareTo(List.get(p)) > 0){
            swap(idx, p);
            upheap(p);
        }
    }

    //deleting largest value (root val)
    public T remove() throws Exception{
        if(List.isEmpty()){
            throw new Exception("Heap is empty");
        }

        T temp = List.get(0);

        T last = List.remove(List.size()-1);
        if(!List.isEmpty()){
            List.set(0, last);
            downheap(0);
        }

        return temp;
    }

    private void downheap(int idx){
        int max = idx;
        int l = left(idx);
        int r = right(idx);

        if(l < List.size() && List.get(max).compareTo(List.get(l)) < 0){
            max = l;
        }

        if(r < List.size() && List.get(max).compareTo(List.get(r)) < 0){
            max = r;
        }

        if(max != idx){
            swap(max, idx);
            downheap(max);
        }
    }


    //heapsort
    public ArrayList<T> heapSort() throws Exception{
        ArrayList<T> data = new ArrayList<>();

        while(!List.isEmpty()){
            data.add(this.remove());
        }

        return data;
    }
}

public class DS22_Max_Heap {
    public static void main(String[] args) throws Exception{
        MaxHeap<Integer> heap = new MaxHeap<>();

        heap.insert(34);
        heap.insert(45);
        heap.insert(22);
        heap.insert(89);
        heap.insert(76);

        //removes the largest no.
        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap.remove());

        //heapsort
        ArrayList list = heap.heapSort();
        System.out.println(list);

    }
}
