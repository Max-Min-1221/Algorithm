package class02;

// 链表反转
public class Code01_ReverseList {

    // 定义单向链表的结点Node
    public static class Node{

        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    // 定义双向链表的结点Node
    public static class doubleNode{

        public int value;
        public doubleNode last;
        public doubleNode next;

        public doubleNode(int value) {
            this.value = value;
        }
    }

    // 单向链表的反转
    public static Node reverseLinkedList(Node head){
        Node pre = null;
        Node next = null;

        while (head != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    // 双向链表的反转
    public static doubleNode reverseDoubleList(doubleNode head){
        doubleNode pre = null;
        doubleNode next = null;

        while (head != null){
           next = head.next;
           head.next = pre;
           head.last = next;
           pre = head;
           head = next;
        }
        return pre;
    }
}
