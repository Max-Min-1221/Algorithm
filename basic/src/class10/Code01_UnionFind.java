package class10;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Code01_UnionFind {

    public static class Node<V>{
        V value;
        public Node(V value){
            this.value = value;
        }
    }

    public static class UnionSet<V>{
        // V -> Node<V> 一一对应
        public HashMap<V, Node<V>> nodes;
        // 父节点
        public HashMap<Node<V>, Node<V>> parents;
        // 某一点是代表点的情况下,才会放在这个Map中
        public HashMap<Node<V>, Integer> sizeMap;

        // 初始化过程: 外部传入一个List集合,然后将其值放入Map中
        public UnionSet(List<V> list) {
            for (V value: list){
                Node<V> node = new Node<>(value);
                nodes.put(value, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        // 从点cur开始,一直往上找,找到不能再往上的代表点,返回
        public Node<V> findFather(Node<V> cur){
            // 准备一个容器,存储沿途的点
            Stack<Node<V>> path = new Stack<>();
            while (cur != parents.get(cur)){
                path.push(cur);
                cur = parents.get(cur);
            }
            // 优化: 将沿途遍历的点全部直接连向代表点
            // 可以减少链的长度
            while (!path.isEmpty()){
                parents.put(path.pop(), cur);
            }
            return cur;
        }

        public boolean isSameSet(V a, V b){
            // 先判断有没有这两个值
            if (!nodes.containsKey(a) || !nodes.containsKey(b)){
                return false;
            }
            return findFather(nodes.get(a)) == findFather(nodes.get(b));
        }

        // 合并方法:将a背后的大集合和b背后的大集合进行合并
        public void union(V a, V b){
            if (!nodes.containsKey(a) || !nodes.containsKey(b)){
                return;
            }
            // 找到a,b集合的代表点
            Node<V> aHead = findFather(nodes.get(a));
            Node<V> bHead = findFather(nodes.get(b));
            if (aHead != bHead){
                // 判断a,b集合的大小
                int aSize = sizeMap.get(aHead);
                int bSize = sizeMap.get(bHead);
                Node<V> big = aSize >= bSize ? aHead : bHead;
                Node<V> small = aSize >= bSize ? bHead : aHead;
                parents.put(small, big);
                sizeMap.put(big, aSize + bSize);
                sizeMap.remove(small);
            }
        }
    }
}
