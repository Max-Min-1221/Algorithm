package class03;

public class Code03_Partition {

    public static void main(String[] args) {

        int[] arr1 = {1, 23, 4, 54, 54, 562, 31, 14, 5, 6, 7, 2, 20, 20, 20};
        System.out.println(partition1(arr1, 0, arr1.length - 1));
        for (int i : arr1) {
            System.out.print(i + "-");
        }

        System.out.println();
        int[] arr2 = {1, 23, 4, 54, 54, 562, 31, 14, 5, 15, 6, 7, 2, 20, 20, 20};
        System.out.println(partition2(arr2, 0, arr2.length - 1, 15));
        for (int i : arr2) {
            System.out.print(i + "=");
        }

        System.out.println();
        int[] arr3 = {1, 23, 4, 54, 54, 562, 31, 14, 5, 6, 7, 2, 20, 20, 20};
        int[] ans = netherlandsFlag(arr3, 0, arr3.length - 1);
        for(int i: ans){
            System.out.print("ans =" + i + ",");
        }
        for(int i: arr3){
            System.out.print(i + "..");
        }

        System.out.println();
        int[] arr4 = {1, 23, 4, 54, 54, 562, 31, 14, 5, 14, 6, 7, 2, 20, 20, 20};
        int[] res = netherlandsFlag2(arr4, 0, arr4.length - 1, 14);
        for(int i: res){
            System.out.print("res =" + i + ",");
        }
        for(int i: arr4){
            System.out.print(i + "..");
        }
    }

    public static int partition1(int[] arr, int L, int R) {
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

    // 自定义划分值
    public static int partition2(int[] arr, int L, int R, int num) {

        if (L > R) {
            return -1;
        }
        if (L == R) {
            return L;
        }
        int lessEqual = L - 1;
        int index = L;
        while (index < R) {
            if (arr[index] <= num) {
                swap(arr, ++lessEqual, index);
            }
            index++;
        }

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

    public static int[] netherlandsFlag2(int[] arr, int L, int R, int num) {
        if(L > R){
            return new int[] {-1, -1};
        }
        if(L == R){
            return new int[] {L, R};
        }
        int less = L - 1; // < 区右边界
        int more = R + 1; // > 区左边界
        int index = L;
        while(index < more){
            if(arr[index] < num){
                swap(arr, ++less, index++);
            }else if(arr[index] > num){
                swap(arr, --more, index);
            }else {
                index++;
            }
        }
        return new int[] {less + 1, more - 1};
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
