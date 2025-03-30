package Data_Structures;

import java.util.ArrayList;

//implementation of min heap
class MinHeap<T extends Comparable<T>> {
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

        if(List.get(idx).compareTo(List.get(p)) < 0){
            swap(idx, p);
            upheap(p);
        }
    }

    //deleting samllest value (root val)
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
        int min = idx;
        int l = left(idx);
        int r = right(idx);

        if(l < List.size() && List.get(min).compareTo(List.get(l)) > 0){
            min = l;
        }

        if(r < List.size() && List.get(min).compareTo(List.get(r)) > 0){
            min = r;
        }

        if(min != idx){
            swap(min, idx);
            downheap(min);
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

public class DS21_Min_Heap {
    public static void main(String[] args) throws Exception{
        MinHeap<Integer> heap = new MinHeap<>();

        heap.insert(34);
        heap.insert(45);
        heap.insert(22);
        heap.insert(89);
        heap.insert(76);

        //removes the smallest no.
        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap.remove());

        //heapsort
        ArrayList list = heap.heapSort();
        System.out.println(list);

    }
}

// Priority Queue can be implemented using Heap, LinkedList
// but Heap is recomended ...