package class09;

import java.util.PriorityQueue;

public class Code03_LessMoneySplitGold {

    public static int lessMoney(int[] arr){
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++){
            queue.add(arr[i]);
        }
        int sum = 0;
        int cur = 0;
        while (queue.size() > 1){ // 保证小根堆里至少有两个数
            cur = queue.poll() + queue.poll();
            sum += cur;
            queue.add(cur);
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] arr = {10, 20, 30};
        System.out.println(lessMoney(arr));
    }
}
