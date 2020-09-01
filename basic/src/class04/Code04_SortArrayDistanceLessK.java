package class04;

import java.util.PriorityQueue;

public class Code04_SortArrayDistanceLessK {

    public static void sortArrayDistanceLessK(int[] arr, int K){
        if (K == 0){
            return;
        }
        // 创建小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;
        // 先放K个数或者length-1个数
        for (; index <= Math.min(arr.length - 1, K - 1); index++){
            heap.add(arr[index]);
        }
        int i = 0;
        // 放入堆结构再取出的循环过程
        for (; i < arr.length; i++, index++){
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }
        // 最后剩几个数，直接取出
        while (!heap.isEmpty()){
            arr[i++] = heap.poll();
        }
    }
}