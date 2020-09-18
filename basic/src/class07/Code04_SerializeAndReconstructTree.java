package class07;

import java.util.LinkedList;
import java.util.Queue;

public class Code04_SerializeAndReconstructTree {

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value){
            this.value = value;
        }
    }

    // 先序遍历进行序列化
    public static Queue<String> preSerialize(Node head){
        Queue<String> queue = new LinkedList<>();
        pres(queue, head);
        return queue;
    }

    public static void pres(Queue<String> queue, Node node){
        if (node == null){
            queue.add(null);
        }else {
            queue.add(String.valueOf(node.value));
            pres(queue, node.left);
            pres(queue, node.right);
        }
    }

    // 先序遍历方式进行反序列化
    public static Node buildByPreQueue(Queue<String> preList){
        if (preList == null || preList.size() == 0){
            return null;
        }
        return preb(preList);
    }

    public static Node preb(Queue<String> preList){
        // 从队列中取出数据,判断是否为空
        String value = preList.poll();
        if (value == null){
            return null;
        }
        // 建立节点
        Node head = new Node(Integer.valueOf(value));
        head.left = preb(preList);
        head.right = preb(preList);
        return head;
    }

    // 按层遍历进行序列化-宽度优先遍历
    public static Queue<String> levelSerialize(Node head){
        // 用来进行序列化的队列: 最后返回该队列
        Queue<String> ans = new LinkedList<>();
        if (head == null){
            ans.add(null);
        }else {
            // 用来遍历Node的队列
            Queue<Node> queue = new LinkedList<>();
            ans.add(String.valueOf(head.value));
            queue.add(head);
            while (!queue.isEmpty()){
                head = queue.poll();
                // 有左子树,既进行序列化,又要加入遍历队列
                if (head.left != null){
                    queue.add(head.left);
                    ans.add(String.valueOf(head.left.value));
                }else {
                    // 没有左子树,只进行序列化,加null
                    ans.add(null);
                }
                // 有右子树,既进行序列化,又要加入遍历队列
                if (head.right != null){
                    queue.add(head.right);
                    ans.add(String.valueOf(head.right.value));
                }else {
                    // 没有右子树,只进行序列化,加null
                    ans.add(null);
                }
            }
        }
        return ans;
    }

    public static Node buildByLevelQueue(Queue<String> levelList){
        if (levelList == null || levelList.size() == 0){
            return null;
        }
        Node head = generateNode(levelList.poll());
        // 如果为空,直接返回，不为空继续往下走
        if (head == null){
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        // 因为head是要返回的值,所以不能变,多准备一个变量
        Node node = null;
        while (!queue.isEmpty()){
            node = queue.poll();
           // 创建node的左右子树,不论什么情况都要建立
           node.left = generateNode(levelList.poll());
           node.right = generateNode(levelList.poll());
           // 有左子树时,加入到队列中
           if (node.left != null){
               queue.add(node.left);
           }
           if (node.right != null){
               queue.add(node.right);
           }
        }
        return head;
    }

    private static Node generateNode(String val){
        // 如果是空,就返回空,否则建立一个真正的节点
        if (val == null){
            return null;
        }
        return new Node(Integer.valueOf(val));
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        Queue<String> queue = preSerialize(head);
        for (String str: queue){
            System.out.println(str);
        }

    }
}
