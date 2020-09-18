package class08;

import java.util.LinkedList;
import java.util.Queue;

public class Code06_IsCBT {

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static boolean isCBT1(Node head){
        if (head == null){
            return true;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        // 是否遇到过左右两个孩子不双全的节点
        // 遇到就改成true，并且无法改回来，相当于一个开关
        boolean flag = false;
        Node cur = null;
        Node left = null;
        Node right = null;
        while (!queue.isEmpty()){
            cur = queue.poll();
            left = cur.left;
            right = cur.right;
            if ((left == null && right != null) ||
                // 遇到了不双全的节点，并且当前节点不是叶节点
                    (flag && (left != null || right != null))){
                return false;
            }
            if (left != null){
                queue.add(left);
            }
            if (right != null){
                queue.add(right);
            }
            // 当遇到不双全的节点,flag改成true
            if (left == null || right == null){
                flag = true;
            }
        }
        return true;
    }

    public static boolean isCBT2(Node head){
        if (head == null){
            return true;
        }
        return process(head).isCBT;
    }

    public static class Info{
        public boolean isFull;
        public boolean isCBT;
        public int height;

        public Info(boolean isFull, boolean isCBT, int height) {
            this.isFull = isFull;
            this.isCBT = isCBT;
            this.height = height;
        }
    }
    public static Info process(Node node){
        if (node == null){
            return new Info(true, true, 0);
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;

        boolean isFull = leftInfo.isFull &&
                rightInfo.isFull &&
                (leftInfo.height == rightInfo.height);

        boolean isCBT = false;
        if (isFull){
            isCBT = true;
        }else { // 以node为头,整棵树不满的情况
            if (leftInfo.isCBT && rightInfo.isCBT){ // 大前提：左右子树都是完全二叉树,底下细分三种情况
                if (leftInfo.isCBT && rightInfo.isFull && leftInfo.height == rightInfo.height + 1 ||
                        (leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) ||
                        (leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height)){
                    isCBT = true;
                }
            }
        }
        return new Info(isFull, isCBT, height);
    }
}
