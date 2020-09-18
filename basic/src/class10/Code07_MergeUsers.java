package class10;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Code07_MergeUsers {

    public static class User{
        public String a;
        public String b;
        public String c;

        public User(String a, String b, String c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    public static class Node<V>{
        public V value;

        public Node(V value) {
            this.value = value;
        }
    }

    public static class UnionSet<V>{
        public HashMap<V, Node<V>> nodes;
        public HashMap<Node<V>, Node<V>> parentMap;
        public HashMap<Node<V>, Integer> sizeMap;

        public UnionSet(List<V> list) {
            for (V value: list){
                Node<V> node = new Node<V>(value);
                nodes.put(value, node);
                parentMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public Node<V> findFather(Node<V> node){
            if (node == null){
                return null;
            }
            Stack<Node<V>> path = new Stack<>();
            path.push(node);
            Node<V> cur = node;
            while (cur != parentMap.get(cur)){
                path.push(cur);
                cur = parentMap.get(cur);
            }
            while (!path.isEmpty()){
                parentMap.put(path.pop(), cur);
            }
            return cur;
        }

        public boolean isSameSet(V a, V b){
            if (!nodes.containsKey(a) || !nodes.containsKey(b)){
                return false;
            }
            return findFather(nodes.get(a)) == findFather(nodes.get(b));
        }

        public void union(V a, V b){
            if (!nodes.containsKey(a) || !nodes.containsKey(b)){
                return;
            }
            Node<V> aHead = findFather(nodes.get(a));
            Node<V> bHead = findFather(nodes.get(b));
            if (aHead != bHead){
                int aSize = sizeMap.get(aHead);
                int bSize = sizeMap.get(bHead);
                Node<V> big = aSize >= bSize ? aHead : bHead;
                Node<V> small = aSize >= bSize ? bHead : aHead;
                parentMap.put(small, big);
                sizeMap.put(big, aSize + bSize);
                sizeMap.remove(small);
            }
        }

        // 查询集合的数量
        public int getSetNum(){
            return sizeMap.size();
        }
    }

    public static int mergeUsers(List<User> users){
        UnionSet<User> unionSet = new UnionSet<>(users);
        Map<String, User> aMap = new HashMap<>();
        Map<String, User> bMap = new HashMap<>();
        Map<String, User> cMap = new HashMap<>();
        for (User user : users){
            if (!aMap.containsKey(user.a)){
                aMap.put(user.a, user);
            }else {
                unionSet.union(user, aMap.get(user.a));
            }
            if (!bMap.containsKey(user.b)){
                bMap.put(user.b, user);
            }else {
                unionSet.union(user, bMap.get(user.b));
            }
            if (!cMap.containsKey(user.c)){
                cMap.put(user.c, user);
            }else {
                unionSet.union(user, cMap.get(user.c));
            }
        }
        // 向并查集询问,合并之后,还有多少个集合
        return unionSet.getSetNum();
    }
}
