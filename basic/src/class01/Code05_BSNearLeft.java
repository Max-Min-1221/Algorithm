package class01;

public class Code05_BSNearLeft {

    public static void main(String[] args) {
        int[] arr = {1,1,1,2,2,2,2,3,3,3,5,5,5,5,6,6,6,6,7};
        System.out.println(nearestIndex(arr, 3));
    }

    // 在arr上，找满足 >=value的最左位置的下标
    public static int nearestIndex(int[] sortedArr, int value){
        int left = 0;
        int right = sortedArr.length - 1;
        int mid = 0;
        int index = -1; // 记录最左的对号

        while (left <= right){
            mid = left + ((right - left) >> 1);
            if(sortedArr[mid] >= value){
                index = mid;
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
        return index;
    }
}
