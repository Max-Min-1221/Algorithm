package class01;

// 使用二分法判断一个数在数组中是否存在
public class Code04_BSExist {

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 50, 100};
        System.out.println(index(arr1, 100));
        int[] arr2 = {1, 2, 3, 4, 50, 100};
        System.out.println(exist(arr1, 100));
    }

    // 判断某个数是否存在
    public static boolean exist(int[] sortedArr, int value){
        if(sortedArr == null || sortedArr.length == 0){
            return false;
        }

        int left = 0;
        int right = sortedArr.length - 1;
        int mid = 0;

        while (left < right){
            mid = left + ((right - left) >> 1);
            if (sortedArr[mid] == value){
                return true;
            }else if (sortedArr[mid] > value){
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
        return sortedArr[left] == value;
    }

    // 求出某个数所在下标，不存在则返回-1
    public static int index(int[] sortedArr, int value){
        if(sortedArr == null || sortedArr.length == 0){
            return -1;
        }

        int left = 0;
        int right = sortedArr.length - 1;
        int mid = 0;

        while (left <= right){
            mid = left + ((right - left) >> 1);
            if (sortedArr[mid] == value){
                return mid;
            }else if (sortedArr[mid] > value){
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
