package class07;

public class Code07_SuccessorNode {

    public static class Node{
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node getSuccessorNode(Node node){
        if (node == null){
            return node;
        }

        if (node.right != null){
            Node cur = node.right;
            while (cur.left != null){
                cur = cur.left;
            }
            return cur;
        }else {
            Node parent = node.parent;
            // 当前节点是其父节点的右孩子
            while (parent != null && node != parent.left){
                // 两个节点同时往上一步
                node = node.parent;
                parent = node.parent;
            }
            return parent;
        }
    }
}
