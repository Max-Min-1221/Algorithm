package class11;

import java.util.Stack;

public class Code04_ReverseStackUsingRecursive {

    public static void reverse(Stack<Integer> stack){
        if (stack.isEmpty()){
            return;
        }
        int i = bottom(stack);
        reverse(stack);
        stack.push(i);
    }

    // 首先考虑一个弹出栈底元素的函数
    public static int bottom(Stack<Integer> stack){
        int result = stack.pop();
        if (stack.isEmpty()){
            return result;
        }else {
            int last = bottom(stack);
            stack.push(result);
            return last;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        reverse(stack);
        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }
}
