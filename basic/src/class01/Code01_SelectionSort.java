package class01;

public class Code01_SelectionSort {

    public static void main(String[] args) {

        int[] arr = {12, 7, 20, 5, 24};
        selectionSort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    public static void selectionSort(int[] arr){

        if(arr == null || arr.length < 2){
            return;
        }
        for(int i = 0; i < arr.length - 1; i++){
            int minIndex = i;
            for(int j = i + 1; j < arr.length; j++){
                if(arr[minIndex] > arr[j]){
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
