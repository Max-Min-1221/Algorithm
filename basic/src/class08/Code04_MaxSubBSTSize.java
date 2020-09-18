package class08;

public class Code04_MaxSubBSTSize {

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static int maxSubBSTSize(Node head){
        return process(head).maxSubBSTSize;
    }

    // 信息结构体需要四个信息,求全集
    public static class Info{
        public int maxSubBSTSize;
        public boolean isAllBST;
        public int max;
        public int min;

        public Info(int maxSubBSTSize, boolean isAllBST, int max, int min) {
            this.maxSubBSTSize = maxSubBSTSize;
            this.isAllBST = isAllBST;
            this.max = max;
            this.min = min;
        }
    }

    public static Info process(Node node){
        // 如果node为null的话,最大值和最小值不好确定,所以直接返回null
        // 如果返回null,那么接下来每一步都要做判断是否为null
        if (node == null){
            return null;
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        // 求最大值和最小值
        int max = node.value;
        int min = node.value;
        if (leftInfo != null){
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
        }
        if (rightInfo != null){
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
        }

        // 求最大二叉搜索子树的大小
        // 如果不经过X节点的结果
        int maxSubBSTSize = 0;
        if (leftInfo != null){
            maxSubBSTSize = leftInfo.maxSubBSTSize;
        }
        if (rightInfo != null){
            maxSubBSTSize = Math.max(maxSubBSTSize, rightInfo.maxSubBSTSize);
        }

        boolean isAllBST = false;
        // 如果子树是null,并不会影响搜索二叉树,条件都为true即可
        // 下面条件即为X为头节点时的情况
        if ((leftInfo == null ? true : leftInfo.isAllBST)
                &&
                (rightInfo == null ? true : rightInfo.isAllBST)
                &&
                (leftInfo == null ? true : leftInfo.max < node.value)
                &&
                (rightInfo == null ? true : rightInfo.min > node.value)){
            maxSubBSTSize =
                    (leftInfo == null ? 0 : leftInfo.maxSubBSTSize)
                    + (rightInfo == null ? 0 : rightInfo.maxSubBSTSize)
                    + 1;
            isAllBST = true;
        }
        return new Info(maxSubBSTSize, isAllBST, max, min);
    }
}
