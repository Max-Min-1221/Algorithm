package class08;

public class Code01_isBalanced {

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static boolean isBalanced(Node head){
        return process(head).isBalanced;
    }

    // 左、右要求都一样
    // Info: 信息返回的结构体
    public static class Info{
        public boolean isBalanced;
        public int height;

        public Info(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    // 因为还要判断node自身是否是平衡的,所以返回的也是Info类型
    public static Info process(Node node){
        if (node == null){
            return new Info(true, 0);
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isBalanced = true;
        if (!leftInfo.isBalanced || rightInfo.isBalanced ||
            Math.abs(leftInfo.height - rightInfo.height) > 1){
            isBalanced = false;
        }
        return new Info(isBalanced, height);
    }
}
