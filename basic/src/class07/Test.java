package class07;

public class  Test {

    public static void main(String[] args) {
        int[] arr = {9,3,21,0,12,88,45,32,0,56,0,3,0,0};
        int index = partition(arr, 0, arr.length - 1);
        System.out.println("index = " + index);
        for (int i : arr){
            System.out.println(i);
        }
    }

    public static int partition(int[] arr, int L, int R){
        if (arr.length < 1  || (arr.length < 2 && arr[0] != 0)){
            throw new IllegalArgumentException("输入的不正确");
        }
        int zone0 = L - 1;
        int index = L;
        while (index <= R){
            if (arr[index] == 0){
                swap(arr, ++zone0, index++);
            }else {
                index++;
            }
        }
        return zone0;
    }

    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
