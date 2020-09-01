package class02;

import java.util.Stack;

public class Code05_GetMinStack {

    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push(5);
        stack.push(10);
        stack.push(9);
        stack.push(4);
        stack.push(20);
        System.out.println(stack.getMin());
    }

    // 自定义栈
    public static class MyStack{
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MyStack() {
            this.stackData = new Stack<>();
            this.stackMin = new Stack<>();
        }

        // 最小栈放入数据时，可能有三种情况
        // 栈为空，大于栈顶，小于栈顶
        public void push(Integer num){
            this.stackData.push(num);
            if(this.stackMin.isEmpty()){
                this.stackMin.push(num);
            }else if(num < this.stackMin.peek()){
                this.stackMin.push(num);
            }else{
                int min = this.stackMin.peek();
                this.stackMin.push(min);
            }
        }

        public Integer pop(){
            if(this.stackData.isEmpty()){
                throw new RuntimeException("栈已空，无数据");
            }
            int value = stackData.pop();
            // 删除栈顶元素
            if(value == this.getMin()){
                this.stackMin.pop();
            }
            return value;
        }

        // 永远返回栈顶元素
        private Integer getMin() {
            if(this.stackMin.isEmpty()){
                throw new RuntimeException("栈已空，无数据");
            }
            return this.stackMin.peek();
        }
    }
}
