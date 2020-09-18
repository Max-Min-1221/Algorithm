package class08;

public class Code02_IsFull {

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static boolean isFull(Node head){
        if (head == null){
            return false;
        }
        Info info = process(head);
        // 位运算,相当于2的L次方
        return (1 << info.height) - 1 == info.nodes;
    }

    public static class Info{
        public int nodes;
        public int height;

        public Info(int nodes, int height) {
            this.nodes = nodes;
            this.height = height;
        }
    }

    public static Info process(Node node){
        if (node == null){
            return new Info(0, 0);
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int nodes = leftInfo.nodes + rightInfo.nodes + 1;
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        return new Info(nodes, height);
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);

        boolean flag = isFull(head);
        System.out.println(flag);
    }
}
