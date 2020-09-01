package class02;

// 双向链表实现栈和队列
public class Code03_DoubleEndsListToStackAndQueue {

    public static class Node{

        public int value;
        public Node last;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    // 定义双向链表中的四个方法
    // 从头部添加、删除
    // 从尾部添加、删除
    public static class DoubleEndsList{
        public Node head;
        public Node tail;

        public void addFromHead(int value){
            Node cur = new Node(value);

            if(head == null){
                head = cur;
                tail = cur;
            }else {
                cur.next = head;
                head.last = cur;
                head = cur;
            }
        }

        public void addFromBottom(int value){
            Node cur = new Node(value);

            if(head == null){
                head = cur;
                tail = cur;
            }else {
                cur.last = tail;
                tail.next = cur;
                tail = cur;
            }
        }

        public Integer popFromHead(){
            if(head == null){
                return null;
            }
            Node cur = head;
            if(head == tail){
                head = null;
                tail = null;
            }else{
                head = head.next;
                head.last = null;
                cur.next = null;
            }
            return cur.value;
        }

        public Integer popFromBottom(){
            if(head == null){
                return null;
            }
            Node cur = tail;
            if(head == tail){
                head = null;
                tail = null;
            }else{
                tail = tail.last;
                tail.next = null;
                cur.last = null;
            }
            return cur.value;
        }
    }

    // 栈
    public static class MyStack{
        private DoubleEndsList list;

        public MyStack() {
            list = new DoubleEndsList();
        }

        public void push(Integer value){
            list.addFromBottom(value);
        }

        public Integer pop(){
            return list.popFromHead();
        }
    }

    // 队列
    public static class MyQueue{
        private DoubleEndsList list;

        public MyQueue() {
            list = new DoubleEndsList();
        }

        public void push(Integer value){
            list.addFromHead(value);
        }

        public Integer pop(){
            return list.popFromBottom();
        }
    }
}
