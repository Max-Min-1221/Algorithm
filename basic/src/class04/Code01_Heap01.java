package class04;


// 堆结构
public class Code01_Heap01 {

    // 首先定义出堆结构(用数组实现的完全二叉树结构)
    public static class MyMaxHeap{
        private int[] heap;
        private final int limit;
        private int heapSize;

        public MyMaxHeap(int limit) {
            heap = new int[limit];
            this.limit = limit;
            heapSize = 0;
        }

        public boolean isEmpty(){
            return heapSize == 0;
        }

        public boolean isFull(){
            return heapSize == limit;
        }

        public void posh(int value){
            if(heapSize == limit){
                throw new RuntimeException("堆已满，不能再放入");
            }
            heap[heapSize] = value;
            // 添加数据之后，堆的长度要加1
            heapInsert(heap, heapSize++);
        }

        private void heapInsert(int[] arr, int index) {

            // arr[index] 不比 arr[index父]大时，停止
            while(arr[index] > arr[(index - 1) / 2]){
                swap(arr, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        // 返回最大值，并且在大顶堆中将最大值删除
        // 返回后，结构依然是大顶堆结构
        public int pop(){
            if(heapSize == 0){
                throw new RuntimeException("堆已空，无数据");
            }
            int ans = heap[0];
            swap(heap, 0, --heapSize);
            // 这里将heapSize也传入是判断循环结束条件的
            heapify(heap, 0, heapSize);
            return ans;
        }

        // 从index位置，往下看，不断的下沉
        // 停止条件：index的孩子不再比它大，或者没有孩子了
        private void heapify(int[] arr, int index, int heapSize) {
            int left = index * 2 + 1;
            while (left < heapSize){
                // 两个孩子中，谁的值大，把下标给largest
                // 1）只有左孩子，left -> largest
                // 2) 同时有左孩子和右孩子，右孩子的值<= 左孩子的值，left -> largest
                // 3) 同时有左孩子和右孩子并且右孩子的值> 左孩子的值， right -> largest
                int largest = left + 1 < heapSize && arr[left] < arr[left + 1] ?
                            left + 1 : left;
                // 父和较大的孩子之间，谁的值大，把下标给largest
                int allLargest = arr[index] < arr[largest] ? largest : index;
                // 说明没有发生交换
                if(allLargest == index){
                    break;
                }
                index = allLargest;
                left = index * 2 + 1;
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        MyMaxHeap maxHeap = new MyMaxHeap(100);
        maxHeap.posh(10);
        maxHeap.posh(9);
        maxHeap.posh(7);
        maxHeap.posh(15);
        maxHeap.posh(3);
        maxHeap.posh(15);
    }
}
