package class10;

import java.util.*;

public class Code04_Kruskal {

    public static class UnionFind{
        public static HashMap<Node, Node> parentMap;
        public static HashMap<Node, Integer> sizeMap;

        public UnionFind() {
            parentMap = new HashMap<>();
            sizeMap = new HashMap<>();
        }

        public void getSet(Collection<Node> collection){
            parentMap.clear();
            sizeMap.clear();
            for (Node node : collection){
                parentMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public Node findFather(Node node){
            if (node == null){
                return null;
            }
            Stack<Node> stack = new Stack<>();
            stack.push(node);
            while (node != parentMap.get(node)){
                node = parentMap.get(node);
                stack.push(node);
            }
            while (!stack.isEmpty()){
                parentMap.put(stack.pop(), node);
            }
            return node;
        }

        public boolean isSameSet(Node n1, Node n2){
            return findFather(n1) == findFather(n2);
        }

        public void union(Node n1, Node n2){
            if (n1 == null || n2 == null){
                return;
            }
            Node parent1 = parentMap.get(n1);
            Node parent2 = parentMap.get(n2);
            if (parent1 != parent2){
                int size1 = sizeMap.get(n1);
                int size2 = sizeMap.get(n2);
                Node big = size1 >= size2 ? parent1 : parent2;
                Node small = size1 >= size2 ? parent2 : parent1;
                parentMap.put(small, big);
                sizeMap.put(big, size1 + size2);
                sizeMap.remove(small);
            }
        }
    }

    public static class EdgeComparator implements Comparator<Edge>{

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static Set<Edge> kruskalSMT(Graph graph){
        UnionFind unionFind = new UnionFind();
        unionFind.getSet(graph.nodes.values());
        PriorityQueue<Edge> queue = new PriorityQueue<>(new EdgeComparator());
        for (Edge edge : graph.edges){ // 把所有的边按从小到大顺序放入小根堆中
            queue.add(edge);  // O(logN)
        }
        Set<Edge> result = new HashSet<>();
        while (!queue.isEmpty()){ // N条边
            Edge cur = queue.poll(); // O(logN)
            if (!unionFind.isSameSet(cur.from, cur.to)){
                result.add(cur);
                unionFind.union(cur.from, cur.to);
            }
        }
        return result;
    }
}
