package class06;

import java.util.HashMap;
import java.util.Map;

public class Code04_CopyListWithRandom {

    public static class Node{
        public int value;
        public Node next;
        public Node random;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node copyListWithRandom1(Node head){

        Map<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null){
            map.put(cur, new Node(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur != null){
            // cur: 老
            // map.get(cur): 新
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
        }

        return map.get(head);
    }

    public static Node copyListWithRandom2(Node head){
        if (head == null){
            return null;
        }
        Node cur = head;
        Node next = null;
        while (cur != null){
            // 从1 -> 2 变为 1 -> 1' -> 2
            next = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = next;
            cur = next;
        }
        // 串好之后找出新的random
        cur = head;
        Node curCopy = null;
        while (cur != null){
            next = cur.next.next;
            curCopy = cur.next;
            curCopy.random = cur.random == null ? null : cur.random.next;
            cur = next;
        }
        Node res = head.next;
        // 分离
        cur = head;
        while (cur != null){
            next = cur.next.next;
            curCopy = cur.next;
            cur.next = next;
            curCopy.next = next != null ? next.next : null;
            cur = next;
        }
        return res;
    }
}
