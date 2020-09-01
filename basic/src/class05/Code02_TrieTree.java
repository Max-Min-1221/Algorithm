package class05;

import java.util.HashMap;

public class Code02_TrieTree {

    public static class Node{
        public int pass;
        public int end;
        public HashMap<Integer, Node> nexts;

        public Node() {
            pass = 0;
            end = 0;
            nexts = new HashMap<>();
        }
    }

    public static class TrieTree{
        private Node root;

        public TrieTree() {
            root = new Node();
        }

        public void insert(String word){
            if (word == null){
                return;
            }
            char[] str = word.toCharArray();
            Node node = root;
            node.pass++;
            int path = 0;
            for (int i = 0; i < str.length; i++){
                path = (int)str[i];
                if (!node.nexts.containsKey(path)){
                    node.nexts.put(path, new Node());
                }
                node = node.nexts.get(path);
                node.pass++;
            }
            node.end++;
        }

        public void delete(String word){
            if (search(word) != 0){
                char[] str = word.toCharArray();
                Node node = root;
                node.pass--;
                int path = 0;
                for (int i = 0; i < str.length; i++){
                    path = (int)str[i];
                    if (--node.nexts.get(path).pass == 0){
                        node.nexts.remove(path);
                        return;
                    }
                    node = node.nexts.get(path);
                }
                node.end--;
            }
        }

        // word这个字符串出现过几次
        public int search(String word){
            if (word == null){
                return 0;
            }
            char[] str = word.toCharArray();
            Node node = root;
            int path = 0;
            for (int i = 0; i < str.length; i++){
                path = (int)str[i];
                if (!node.nexts.containsKey(path)){
                    return 0;
                }
                node = node.nexts.get(path);
            }
            return node.end;
        }

        // 所加入的字符串中，以pre这个字符串作为前缀的有几个
        public int prefixNumber(String pre){
            if (pre == null){
                return 0;
            }
            char[] str = pre.toCharArray();
            Node node = root;
            int path = 0;
            for (int i = 0; i < str.length; i++){
                path = (int)str[i];
                if (!node.nexts.containsKey(path)){
                    return 0;
                }
                node = node.nexts.get(path);
            }
            return node.pass;
        }
    }

    public static void main(String[] args) {
        System.out.println((int)'我');
    }
}
