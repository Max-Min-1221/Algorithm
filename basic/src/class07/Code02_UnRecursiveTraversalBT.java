package class07;

import java.util.Stack;

public class Code02_UnRecursiveTraversalBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    // 非递归方式进行前序遍历
    public static void pre(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            head = stack.pop();
            System.out.println(head.value);
            if (head.right != null) {
                stack.push(head.right);
            }
            if (head.left != null) {
                stack.push(head.left);
            }
        }
    }

    // 非递归方式进行后序遍历方法1
    public static void pos(Node head){
        if (head != null){
            Stack<Node> stack1 = new Stack<>();
            Stack<Node> stack2 = new Stack<>();
            stack1.push(head);
            while (!stack1.isEmpty()){
                head = stack1.pop();
                stack2.push(head);
                if (head.left != null){
                    stack1.push(head.left);
                }
                if (head.right != null){
                    stack1.push(head.right);
                }
            }
            while (!stack2.isEmpty()){
                System.out.println(stack2.pop().value);
            }
        }
    }

    // 非递归方式进行后序遍历方法2
    public static void pos2(Node h){
        if (h != null){
            Stack<Node> stack = new Stack<>();
            stack.push(h);
            Node c = null;
            while (!stack.isEmpty()){
                c = stack.peek();
                // 第一个逻辑分支：左子树和右子树都没有处理过
                if (c.left != null && c.left != h && c.right != h){
                    stack.push(c.left);
                // 第二个逻辑分支：左子树处理过，现在处理右子树
                }else if (c.right != null && c.right != h){
                    stack.push(c.right);
                // 第三个逻辑分支：左子树和右子树都已经处理完，现在处理自身
                // 打印并弹出，然后让h来到c的位置
                }else {
                    System.out.println(stack.pop().value);
                    h = c;
                }
            }
        }
    }

    // 非递归方式进行中序遍历
    public static void in(Node head){
        if (head != null){
            Stack<Node> stack = new Stack<>();
            // 这里并没有提前放入节点，所以还要再加个条件head!=null
            while (!stack.isEmpty() || head != null){
                if (head != null){
                    stack.push(head);
                    head = head.left;
                }else {
                    head = stack.pop();
                    System.out.println(head.value);
                    head = head.right; // 来到右子树上
                }
            }
        }
    }

    public static void main(String[] args) {

        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        // pre(head);
        // in(head);
        // pos(head);
        pos2(head);
    }
}
