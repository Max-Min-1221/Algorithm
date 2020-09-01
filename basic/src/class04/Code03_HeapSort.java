package class04;

// 堆排序
public class Code03_HeapSort {

    public static void main(String[] args) {
        int[] arr = {1,2,3,41,15,752,121,11,42,4,124,77};
        heapSort(arr);
        for(int i: arr){
            System.out.println(i);
        }
    }

    public static void heapSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        /*for(int i = 0; i < arr.length; i++){
            heapInsert(arr, i);
        }*/
        for(int i = arr.length - 1; i >= 0; i--){
            heapify(arr, i, arr.length);
        }
        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        while(heapSize > 0){
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    private static void heapInsert(int[] arr, int index) {
        while(arr[index] > arr[(index - 1) / 2]){
            swap(arr, index, ((index - 1) / 2));
            index = (index - 1) / 2;
        }
    }

    private static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while(left < heapSize){
            int largest = (left + 1) < heapSize && arr[left] < arr[left + 1] ?
                    left + 1 : left;
            int allLargest = arr[index] < arr[largest] ? largest : index;
            if (allLargest == index){
                break;
            }
            swap(arr, index, largest);
            index = allLargest;
            left = index * 2 + 1;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
