package class02;

public class Code02_DeleteGivenValue {

    // 定义单向链表的结点Node
    public static class Node{

        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node removeValue(Node head, int num){
        // 首先head来到第一个不需要删的位置
        while (head != null){
            if (head.value != num){
                break;
            }
            head = head.next;
        }
        // 定义两个中间变量,因为头结点不能变，需要返回
        Node pre = head;
        Node cur = head;
        while (cur != null){
            if (cur.value == num){
                // 这句相当于删除了cur结点
                pre.next = cur.next;
            }else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }
}
