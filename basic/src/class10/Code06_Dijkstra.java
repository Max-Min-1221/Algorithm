package class10;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Code06_Dijkstra {

    public static HashMap<Node, Integer> dijkstra(Node from){
        // Map里存的是某个点以及源点from到该点的最短距离
        // 如果Map里没有,说明从源点from出发到这个点的距离为正无穷
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        // 一开始只有一条记录
        distanceMap.put(from, 0);
        // 已经求过距离的节点,存在该Set集合中，以后再也不碰
        HashSet<Node> selectedNodes = new HashSet<>();
        Node minNode = minNodeAndUnselectedNode(distanceMap, selectedNodes);
        while (minNode != null){
            for (Edge edge : minNode.edges){
                int distance = distanceMap.get(minNode);
                Node toNode = edge.to;
                // 如果没有这个节点就直接加入
                if (!distanceMap.containsKey(toNode)){
                    distanceMap.put(toNode, distance + edge.weight);
                }else {
                    // 如果有就更新距离
                    distanceMap.put(toNode,
                            Math.min(distanceMap.get(toNode), distance + edge.weight));
                }
            }
            // 将已经用过的点放入Set中,锁住
            selectedNodes.add(minNode);
            // 再找出最小距离的点
            minNode = minNodeAndUnselectedNode(distanceMap, selectedNodes);
        }
        return distanceMap;
    }

    // 从distanceMap中拿出没有被锁住的点,也就是不在selectedNodes集合中的最小距离点
    public static Node minNodeAndUnselectedNode(
            HashMap<Node, Integer> distanceMap,
            HashSet<Node> selectedNodes){
        Node minNode = null;
        int minDistance = Integer.MAX_VALUE;
        // 遍历Map的方式之一
        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()){
            Node node = entry.getKey();
            int distance = entry.getValue();
            if (!selectedNodes.contains(node) && distance < minDistance){
                minNode = node;
                minDistance = distance;
            }
        }
        return minNode;
    }

}
