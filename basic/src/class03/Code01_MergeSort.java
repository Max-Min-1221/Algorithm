package class03;

public class Code01_MergeSort {

    public static void main(String[] args) {
        int[] arr = {1,23,4,5,676,7,5,734,5,235,25,2,41,4,1};
        mergeSort1(arr);
        for(int i: arr){
            System.out.println(i);
        }
    }

    // 递归方法实现归并排序
    public static void mergeSort1(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    // arr[L..R]范围上，变成有序的
    public static void process(int[] arr, int L, int R) {
        if (L == R){
            return;
        }
        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    public static void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        // 将左右组复制到辅助数组help中
        // 小于等于时复制
        while(p1 <= M && p2 <= R){
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++]: arr[p2++];
        }
        // 要么p1越界，要么p2越界
        while(p1 <= M){
            help[i++] = arr[p1++];
        }
        while(p2 <= R){
            help[i++] = arr[p2++];
        }
        // 将复制过来的数组help再返回给原来的数组
        for(i = 0; i < help.length; i++){
            arr[L + i] = help[i];
        }
    }
}