package class04;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Code06_SmallHeapToBigHeap {

    public static class HeapComparator implements Comparator<Integer>{
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }

    public static void main(String[] args) {

        PriorityQueue<Integer> heap = new PriorityQueue<>(new HeapComparator());

        heap.add(9);
        heap.add(7);
        heap.add(13);
        heap.add(90);
        heap.add(55);
        heap.add(3);

        while (!heap.isEmpty()){
            System.out.println(heap.poll());
        }
    }
}