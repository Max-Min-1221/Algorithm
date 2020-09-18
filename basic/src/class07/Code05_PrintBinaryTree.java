package class07;

public class Code05_PrintBinaryTree {

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void printBinaryTree(Node head){
        printInorder(head, 0, "H", 17);
    }

    public static void printInorder(Node head, int height, String to, int len){
        if (head == null){
            return;
        }

        printInorder(head.right, height + 1, "v", len);

        String val = to + head.value + to;
        int rest = len - val.length();
        int l = rest / 2;
        int r = rest - l;
        val = getSpace(l) + val + getSpace(r);
        System.out.println(getSpace(height * len) + val);

        printInorder(head.left, height + 1, "^", len);
    }

    private static String getSpace(int num){
        String space = " ";
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < num; i++){
            str.append(space);
        }
        return str.toString();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(-222222222);
        head.right = new Node(3);
        head.left.left = new Node(Integer.MIN_VALUE);
        head.right.left = new Node(55555555);
        head.right.right = new Node(66);
        head.left.left.right = new Node(777);
        printBinaryTree(head);
    }
}
