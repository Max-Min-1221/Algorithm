package class10;

import java.util.*;

public class Code03_TopologySort {

    public static List<Node> sortedTopology(Graph graph){
        // key: 某一个node  value: node剩余的入度
        Map<Node, Integer> inMap = new HashMap<>();
        // 专门存放入度为0的node
        Queue<Node> zeroInQueue = new LinkedList<>();

        for (Node node : graph.nodes.values()){
            inMap.put(node, node.in);
            if (node.in == 0){
                zeroInQueue.add(node);
            }
        }
        // 拓扑排序的结果,依次放入list中
        List<Node> list = new ArrayList<>();

        while (!zeroInQueue.isEmpty()){
            Node cur = zeroInQueue.poll();
            list.add(cur);
            for (Node next : cur.nexts){
                // 这里是改变inMap里的入度数
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0){
                    zeroInQueue.add(next);
                }
            }
        }
        return list;
    }
}
