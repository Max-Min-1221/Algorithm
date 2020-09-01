package class06;

import java.util.Stack;

public class Code02_IsPalindromeList {

    public static class Node{
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    // 使用栈的方法(简单，需要额外空间，适合笔试做)
    public static boolean isPalindrome1(Node head){
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (cur != null){
            stack.push(cur);
            cur = cur.next;
        }
        while (head != null){
            if (head.value != stack.pop().value){
                return false;
            }
            head = head.next;
        }
        return true;
    }

    // 改进：使用栈的方法(只放入一半)
    public static boolean isPalindrome2(Node head){
        if (head == null || head.next == null){
            return true;
        }
        // 找出中点的后一个或者是下中点
        Node slow = head.next;
        Node fast = head;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        Stack<Node> stack = new Stack<>();
        while (slow != null){
            stack.push(slow);
            slow = slow.next;
        }
        while (!stack.isEmpty()){
            if (stack.pop().value != head.value){
                return false;
            }
            head = head.next;
        }
        return true;
    }
}
