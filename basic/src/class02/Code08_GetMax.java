package class02;

public class Code08_GetMax {

    public static void main(String[] args) {
        int[] arr = {1,2,45,42,22,76,11,99};
        System.out.println(getMax(arr));
    }

    // 求数组中的最大值
    public static int getMax(int[] arr){
        return process(arr, 0, arr.length - 1);
    }

    // 求arr[L..R]上的最大值
    private static int process(int[] arr, int L, int R) {
        // 结束递归条件
        if (L == R){ // arr[L..R]范围上只有一个数，直接返回
            return arr[L];
        }
        int mid = L + ((R - L) >> 1);
        int leftMax = process(arr, L, mid);
        int rightMax = process(arr, mid + 1, R);
        return Math.max(leftMax, rightMax);
    }
}
