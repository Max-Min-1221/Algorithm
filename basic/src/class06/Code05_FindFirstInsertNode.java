package class06;

import java.util.HashSet;
import java.util.Set;

public class Code05_FindFirstInsertNode {

    public static class Node{
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    // 判断单链表有没有环
    // 如果有，返回第一个入环节点，如果没有返回null
    public static Node getLoopNode1(Node head){
        if (head == null || head.next == null || head.next.next == null){
            return null;
        }
        // 其实就是从头开始，但为了不影响接下来的判断条件，所以先走一次
        Node fast = head.next.next;
        Node slow = head.next;
        while (fast != slow){
            if (fast.next == null || fast.next.next == null){
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = head;
        while (fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    public static Node getLoopNode2(Node head){

        Set<Node> set = new HashSet<>();
        Node cur = head;
        while (cur != null){
            if (set.contains(cur)){
                return cur;
            }
            set.add(cur);
            cur = cur.next;
        }
        return null;
    }

    // 两个无环链表，返回第一个相交节点，如果不相交返回null
    public static Node noLoop(Node head1, Node head2){
        if (head1 == null || head2 == null){
            return null;
        }
        int n = 0;
        Node cur1 = head1;
        Node cur2 = head2;
        while (cur1.next != null){
            n++;
            cur1 = cur1.next;
        }
        while (cur2.next != null){
            n--;
            cur2 = cur2.next;
        }
        if (cur1 != cur2){
            return null;
        }
        // n: 链表1减去链表2的长度的值
        cur1 = n > 0 ? head1 : head2; // 谁长,谁的头变成cur1
        cur2 = cur1 == head1 ? head2 : head1; // 谁短,谁的头变成cur2
        n = Math.abs(n);
        // 让长的先走一部分
        while (n != 0){
            n--;
            cur1 = cur1.next;
        }
        while (cur1 != cur2){
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    // 两个有环链表,返回第一个相交节点，如果不相交返回null
    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2){
        Node cur1 = null;
        Node cur2 = null;
        // loop1==loop2时，按照无环链表处理
        if (loop1 == loop2){
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1.next != loop1){
                n++;
                cur1 = cur1.next;
            }
            while (cur2.next != loop2){
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0){
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2){
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        }else {
            cur1 = loop1.next;
            while (cur1 != loop1){
                if (cur1 == loop2){
                    return loop1;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }
}
