package class08;

public class Code05_MaxSubBSTHead {

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value){
            this.value = value;
        }
    }

    public static Node getMaxSubBSTHead(Node head){
        if (head == null){
            return null;
        }
        return process(head).maxSubBSTHead;
    }

    // 每一棵子树
    public static class Info{
        // 如果最大二叉搜索子树的头节点就是整棵树的头节点
        // 那么就是搜索二叉树
        public Node maxSubBSTHead;
        public int maxSubBSTSize;
        public int max;
        public int min;

        public Info(Node maxSubBSTHead, int maxSubBSTSize, int max, int min) {
            this.maxSubBSTHead = maxSubBSTHead;
            this.maxSubBSTSize = maxSubBSTSize;
            this.max = max;
            this.min = min;
        }
    }

    public static Info process(Node node){
        if (node == null){
            return null;
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int max = node.value;
        int min = node.value;
        Node maxSubBSTHead = null;
        int maxSubBSTSize = 0;
        // 这里的最大搜索二叉子树的头节点和大小是与X无关时的情况
        if (leftInfo != null){
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
            maxSubBSTSize = leftInfo.maxSubBSTSize;
            maxSubBSTHead = leftInfo.maxSubBSTHead;
        }
        if (rightInfo != null){
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
            // 如果右树上的二叉搜索子树更大的话
            if (rightInfo.maxSubBSTSize > maxSubBSTSize){
                maxSubBSTHead = rightInfo.maxSubBSTHead;
                maxSubBSTSize = rightInfo.maxSubBSTSize;
            }
        }
        // 下面的if条件就是判断整棵树的头节点node是不是最大搜索二叉子树的头节点
        // leftInfo.maxSubBSTHead == node.left: 判断左树整体是不是搜索二叉树
        if ((leftInfo == null ? true : (leftInfo.maxSubBSTHead == node.left && leftInfo.max < node.value)) &&
                (rightInfo == null ? true : (rightInfo.maxSubBSTHead == node.right && rightInfo.min > node.value))){
            maxSubBSTHead = node;
            maxSubBSTSize = (leftInfo == null ? 0 : leftInfo.maxSubBSTSize) +
                    (rightInfo == null ? 0 : rightInfo.maxSubBSTSize) + 1;
        }
        return new Info(maxSubBSTHead, maxSubBSTSize, max, min);
    }
}
