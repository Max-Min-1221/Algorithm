package class01;

public class Code03_InsertionSort {

    public static void main(String[] args) {
        int[] arr = {12, 7, 20, 5, 24, 11, 23, -14, 9};
        insertionSort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    public static void insertionSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }

        for(int i = 1; i < arr.length; i++){
            for(int j = i - 1 ; j >= 0; j--){
                if(arr[j] > arr[j + 1]){
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
