package class03;

public class Code02_SmallSum {

    public static int smallSum(int[] arr){
        if(arr == null || arr.length < 2){
            return 0;
        }
        return process(arr, 0, arr.length);
    }

    public static int process(int[] arr, int L, int R){
        // 当L = R 时，说明只有一个数，没有右组，返回0
        if (L == R){
            return 0;
        }
        int mid = L + ((R - L) >> 1);
        return process(arr, L, mid) +
                process(arr, mid + 1, R)
                + merge(arr, L, mid, R);
    }

    public static int merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int i = 0;
        int ans = 0;
        int p1 = l;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= r){
            ans += arr[p1] < arr[p2] ? arr[p1] * (r - mid) : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid){
            help[i++] = arr[p1++];
        }
        while (p2 <= r){
            help[i++] = arr[p2++];
        }
        for(i = 0; i < help.length; i++){
            arr[l + i] = help[i];
        }

        return ans;
    }
}
