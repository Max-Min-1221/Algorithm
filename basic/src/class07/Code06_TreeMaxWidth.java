package class07;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Code06_TreeMaxWidth {

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value){
            this.value = value;
        }
    }

    public static int maxWidthUseMap(Node head){
        if (head == null){
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        // key:节点  value:节点所在的层数
        Map<Node, Integer> map = new HashMap<>();
        map.put(head, 1);
        int curLevel = 1;  // 当前正在统计哪一层的宽度
        int curLevelNodes = 0; // 当前层curLevel,宽度目前是多少
        int max = 0;
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            int curNodeLevel = map.get(cur); // 得到当前要统计的层数
            if (cur.left != null){
                queue.add(cur.left);
                map.put(cur.left, curNodeLevel + 1);
            }
            if (cur.right != null){
                queue.add(cur.right);
                map.put(cur.right, curNodeLevel + 1);
            }
            // 如果当前节点所在层和要统计的层数是同一层,当前层还未统计完
            if (curNodeLevel == curLevel){
                curLevelNodes++;
            // 当前层已经统计完,max开始赋值,并且从下一层开始
            }else {
                max = Math.max(max, curLevelNodes);
                curLevel++;
                curLevelNodes = 1;
            }
        }
        max = Math.max(max, curLevelNodes);
        return max;
    }

    public static int maxWidthNoMap(Node head){
        if (head == null){
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Node curEnd = head; // 当前层的最右节点
        Node nextEnd = null; // 下一层的最右节点
        int curLevelNodes = 0;
        int max = 0;
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            if (cur.left != null){
                nextEnd = cur.left;
                queue.add(cur.left);
            }
            if (cur.right != null){
                nextEnd = cur.right;
                queue.add(cur.right);
            }
            curLevelNodes++;
            // 如果节点走到了这一层的最后一个节点
            if (cur == curEnd){
                max = Math.max(max, curLevelNodes);
                curLevelNodes = 0;
                curEnd = nextEnd;
            }
        }
        return max;
    }
}
