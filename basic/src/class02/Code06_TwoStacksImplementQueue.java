package class02;

import java.util.Stack;

public class Code06_TwoStacksImplementQueue {

    public static class TwoStacksQueue{
        public Stack<Integer> stackPush;
        public Stack<Integer> stackPop;

        public TwoStacksQueue() {
            this.stackPush = new Stack<Integer>();
            this.stackPop = new Stack<Integer>();
        }

        // push栈向pop栈导入数据
        // pop栈要为空、push栈要全部导完
        public void pushToPop(){
            if(stackPop.isEmpty()){
                while(!stackPush.isEmpty()){
                    stackPop.push(stackPush.pop());
                }
            }
        }

        public void add(Integer num){
            stackPush.push(num);
            pushToPop();
        }

        public Integer poll(){
            if(stackPush.isEmpty() && stackPop.isEmpty()){
                throw new RuntimeException("队列已空，无数据");
            }
            pushToPop();
            return stackPop.pop();
        }

        public Integer peek(){
            if(stackPush.isEmpty() && stackPop.isEmpty()){
                throw new RuntimeException("队列已空，无数据");
            }
            pushToPop();
            return stackPop.peek();
        }
    }
}
