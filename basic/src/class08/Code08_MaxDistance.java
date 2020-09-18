package class08;

public class Code08_MaxDistance {

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static int maxDistance(Node head){
        return process(head).maxDistance;
    }

    public static class Info{
        public int maxDistance;
        public int height;

        public Info(int maxDistance, int height) {
            this.maxDistance = maxDistance;
            this.height = height;
        }
    }

    public static Info process(Node node){
        if (node == null){
            return new Info(0, 0);
        }
        // 首先默认左右子树可以给自己两个信息
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        // 利用以上四个信息,加工出自己的信息,将递归连起来
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int maxDistance = Math.max(
                Math.max(leftInfo.maxDistance, rightInfo.maxDistance),
                leftInfo.height + rightInfo.height + 1
        );
        return new Info(maxDistance, height);
    }
}
