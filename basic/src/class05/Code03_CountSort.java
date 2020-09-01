package class05;

public class Code03_CountSort {

    // 有特定要求：例如：数在0~200之间
    public static void countSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        // 判断数组的最大值(在哪个范围)
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++){
            max = Math.max(max, arr[i]);
        }
        int[] bucket = new int[max + 1];
        for (int i = 0; i < arr.length; i++){
            bucket[arr[i]]++;
        }
        int i = 0;
        for (int j = 0; j < bucket.length; j++){
            while (bucket[j]-- > 0){
                arr[i++] = j;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {12,3,3,1,4,14,1,4,5,23,14,14,5,1};
        countSort(arr);
        for (int i: arr){
            System.out.println(i);
        }
    }
}
