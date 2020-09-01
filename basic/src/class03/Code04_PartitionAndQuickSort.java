package class03;

public class Code04_PartitionAndQuickSort {

    public static int partition(int[] arr, int L, int R) {
        if (L > R) {
            return -1;
        }
        if (L == R) {
            return L;
        }
        int lessEqual = L - 1;
        int index = L;
        while (index < R) {
            if (arr[index] <= arr[R]) {
                swap(arr, ++lessEqual, index);
            }
            index++;
        }
        // 因为这是以arr[R]为划分值
        // 所以最后还要再将arr[R]与 <= 区的下一个数交换
        swap(arr, ++lessEqual, index);
        // 返回的是 <= 区的最后一位的下标
        return lessEqual;
    }

    // arr[L..R]上玩荷兰国旗问题的划分，以arr[R]做划分值
    // 分为三块： <arr[R]  ==arr[R]  >arr[R]
    // 返回的是 = 区下标
    public static int[] netherlandsFlag(int[] arr, int L, int R) {
        if(L > R){
            return new int[] {-1, -1};
        }
        if(L == R){
            return new int[] {L, R};
        }
        int less = L - 1; // < 区右边界
        // 右边界先将arr[R]包住，先不考虑，最后再换
        int more = R; // > 区左边界
        int index = L;
        while(index < more){
            if(arr[index] < arr[R]){
                swap(arr, ++less, index++);
            }else if(arr[index] > arr[R]){
                swap(arr, --more, index);
            }else {
                index++;
            }
        }
        // 因为这是以arr[R]为划分值
        // 所以最后还要再将arr[R]与 = 区的最右边的数交换
        // 交换前：L..less  less+1..more-1  more..R-1 R
        swap(arr, more, R);
        // 交换后：L..less  less+1..more  more+1..R
        return new int[] {less + 1, more};
    }

    public static void quickSort1(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        process1(arr, 0, arr.length - 1);
    }

    public static void process1(int[] arr, int L, int R) {
        if(L >= R){
            return;
        }
        int mid = partition(arr, L, R);
        process1(arr, L, mid - 1);
        process1(arr, mid + 1, R);
    }

    public static void quickSort2(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        process2(arr, 0, arr.length - 1);
    }

    public static void process2(int[] arr, int L, int R) {
        if(L >= R){
            return;
        }
        int equalArr[] = netherlandsFlag(arr, L, R);
        process2(arr, L, equalArr[0] - 1);
        process2(arr, equalArr[1] + 1, R);
    }

    public static void quickSort3(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        process3(arr, 0, arr.length - 1);
    }

    public static void process3(int[] arr, int L, int R) {
        if(L >= R){
            return;
        }
        swap(arr, (L + (int)(Math.random() * (R - L + 1))), R);
        int[] equalArr = netherlandsFlag(arr, L, R);
        process3(arr, L, equalArr[0] - 1);
        process3(arr, equalArr[1] + 1, R);
    }


    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {1,23,43,115,678,1,13,51};
        quickSort1(arr);
        for(int i: arr){
            System.out.print(i + "-");
        }
        System.out.println();
        quickSort2(arr);
        for(int i: arr){
            System.out.print(i + "=");
        }
    }
}
