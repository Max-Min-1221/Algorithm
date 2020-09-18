package class08;

public class Code07_LowestAncestor {

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node getLowestAncestor(Node head, Node o1, Node o2){
        if (head == null){
            return null;
        }
        return process(head, o1, o2).ans;
    }

    public static class Info{
        public Node ans; // o1和o2最初的交汇点
        public boolean findo1; // 子树上有没有o1
        public boolean findo2; // 子树上有没有o2

        public Info(Node ans, boolean findo1, boolean findo2) {
            this.ans = ans;
            this.findo1 = findo1;
            this.findo2 = findo2;
        }
    }
    public static Info process(Node node, Node o1, Node o2){
        if (node == null){
            return new Info(null, false, false);
        }
        Info leftInfo = process(node.left, o1, o2);
        Info rightInfo = process(node.right, o1, o2);

        boolean findo1 = node == o1 || leftInfo.findo1 || rightInfo.findo1;
        boolean findo2 = node == o2 || leftInfo.findo2 || rightInfo.findo2;

        // o1和o2最初的交汇点
        Node ans = null;
        // 如果左树上发现了最初的交汇点
        if (leftInfo.findo1){
            ans = leftInfo.ans;
        }
        // 如果右树上发现了最初的交汇点
        if (rightInfo.findo2){
            ans = rightInfo.ans;
        }
        // 如果左右树都没有交汇点,但在树上仍然找到了o1和o2
        if (ans == null){
            if (findo1 && findo2){
                ans = node;
            }
        }
        return new Info(ans, findo1, findo2);
    }
}
