package class02;

// 使用数组实现队列
public class Code04_RingArray {

    public static class MyQueue{
        private int[] arr;
        private int pushi;
        private int polli;
        private int size;
        private final int limit;

        public MyQueue(int limit) {
            arr = new int[limit];
            pushi = 0;
            polli = 0;
            size = 0;
            this.limit = limit;
        }

        public void push(int value){
            if(size == limit){
                throw new RuntimeException("栈已满，不能再添加数据");
            }
            size++;
            // 此时有空位置，所以直接将value放入数组即可
            arr[pushi] = value;
            // 放完后，数组下标加1
            pushi = nextIndex(pushi);
        }

        public int pop(){
            if(size == 0){
                throw new RuntimeException("栈已空，没有数据了");
            }
            size--;
            int ans = arr[polli];
            polli = nextIndex(polli);
            return ans;
        }

        // 该方法就是将下标加1，当下标满了就从0开始
        private int nextIndex(int i) {
            return i < limit - 1 ? i + 1 : 0;
        }
    }
}
