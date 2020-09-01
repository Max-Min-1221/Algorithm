package class02;

import java.util.LinkedList;
import java.util.Queue;

public class Code07_TwoQueuesImplementStack {

    public static void main(String[] args) {
        TwoQueuesStack<Integer> stack = new TwoQueuesStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println(stack.poll());
        System.out.println(stack.peek());
        System.out.println(stack.poll());
        System.out.println(stack.peek());
        System.out.println(stack.poll());
    }

    public static class TwoQueuesStack<T>{
        public Queue<T> queue;
        public Queue<T> help;

        public TwoQueuesStack() {
            queue = new LinkedList<>();
            help = new LinkedList<>();
        }

        public void push(T value){
            queue.offer(value);
        }

        public T poll(){
            // 将queue中的数据复制到help中,只留一个
            while(queue.size() > 1){
                help.offer(queue.poll());
            }
            T ans = queue.poll();
            // 再复制回去
            Queue<T> temp = queue;
            queue = help;
            help = temp;
            return ans;
        }

        public T peek(){
            while (queue.size() > 1){
                help.offer(queue.poll());
            }
            T ans = queue.poll();
            help.offer(ans);
            Queue<T> temp = queue;
            queue = help;
            help = temp;
            return ans;
        }

        public boolean isEmpty(){
            return queue.isEmpty();
        }
    }
}