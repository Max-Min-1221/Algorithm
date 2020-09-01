package class04;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

// 手动改变堆结构(数据有改变时)
public class Code02_HeapChange {

    public static class MyHeap<T>{
        private ArrayList<T> heap;
        // 用来记录堆中数据的位置
        private HashMap<T, Integer> indexMap;
        private int heapSize;
        private Comparator<? super T> comparator;

        public MyHeap(Comparator<? super T> comparator) {
            heap = new ArrayList<>();
            indexMap = new HashMap<>();
            heapSize = 0;
            this.comparator = comparator;
        }

        public boolean isEmpty(){
            return heapSize == 0;
        }

        public int size(){
            return heapSize;
        }

        public boolean contains(T key){
            return indexMap.containsKey(key);
        }

        public void push(T value){
            heap.add(value);
            // 放入map集合中记录下value所在的位置
            indexMap.put(value, heapSize);
            heapInsert(heapSize++);
        }

        private void heapInsert(int index) {
            while (comparator.compare(heap.get(index), heap.get((index - 1) / 2)) < 0){
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        public T pop(){
            T ans = heap.get(0);
            int end = heapSize - 1;
            swap(0, end);
            heap.remove(end);
            indexMap.remove(ans);
            heapify(0, --heapSize);
            return ans;
        }

        private void heapify(int index, int heapSize) {
            int left = index * 2 + 1;
            while (left < heapSize){
                int largest = (left + 1) < heapSize &&
                        comparator.compare(heap.get(left + 1 ), heap.get(left)) < 0
                        ? left + 1
                        : left;
                largest = comparator.compare(heap.get(largest), heap.get(index)) < 0
                        ? largest
                        : index;
                if (largest == index){
                    break;
                }
                swap(largest, index);
                index = largest;
                left = index * 2 + 1;
            }
        }

        public void resign(T value){
            int index = indexMap.get(value);
            heapInsert(index);
            heapify(index, heapSize);
        }

        private void swap(int i, int j) {
            T o1 = heap.get(i);
            T o2 = heap.get(j);
            indexMap.put(o1, j);
            indexMap.put(o2, i);
            heap.set(i, o2);
            heap.set(j, o1);
        }
    }
}
