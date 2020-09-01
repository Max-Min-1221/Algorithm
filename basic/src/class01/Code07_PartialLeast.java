package class01;

public class Code07_PartialLeast {

    public static void main(String[] args) {
        int[] arr = {5,8,7,9,20};
        System.out.println(getPartialLeastIndex(arr));

        // 不用多余的参数将两个数互换
        int a = 10;
        int b = 100;
        System.out.println("a=" + a + ",b=" + b);
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("a=" + a + ",b=" + b);
    }

    // 给定一个无序，相邻不等数组，求出至少一个局部最小值
    public static int getPartialLeastIndex(int[] arr){
        if (arr == null || arr.length == 0){
            return -1;
        }

        if (arr[0] < arr[1]){
            return 0;
        }

        if (arr[arr.length -1] < arr[arr.length - 2]){
            return arr.length - 1;
        }
        int left = 0;
        int right = arr.length - 1;
        int mid = 0;
        while (left < right){
            mid = left + ((right - left) >> 1);
            if(arr[mid] > arr[mid - 1]){
                right = mid - 1;
            }else if(arr[mid] > arr[mid + 1]){
                left = mid + 1;
            }else {
                return mid;
            }
        }
        return left;
    }
}

