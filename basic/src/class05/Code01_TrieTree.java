package class05;

public class Code01_TrieTree {

    // 首先自定义一个节点
    // 以数组形式实现
    public static class Node{
        public int pass;
        public int end;
        public Node[] nexts;

        public Node() {
            pass = 0;
            end = 0;
            // nexts[i] == null; 说明i方向上的路不存在
            // nexts[i] != null; 说明i方向上的路存在
            nexts = new Node[26];
        }
    }

    // 定义出前缀树
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
            // 只要新加一个字符串，必然从头节点出发
            Node node = root; // node指向头节点
            node.pass++;
            int path = 0;
            for (int i = 0; i < str.length; i++){
                path = str[i] - 'a'; // 看是哪一个字母(放入到数组中)
                if (node.nexts[path] == null){
                    node.nexts[path] = new Node();
                }
                node = node.nexts[path]; // node指向下一个节点
                node.pass++;
            }
            node.end++;
        }

        // 从前缀树上删除某个字符串
        public void delete(String word){
            if (search(word) == 0){
                return;
            }
            Node node = root;
            node.pass--;
            char[] str = word.toCharArray();
            int path = 0;
            for (int i = 0; i < str.length; i++){
                path = str[i] - 'a';
                // 如果下一个节点的pass值为1，则后续全部不要了
                if (--node.nexts[path].pass == 0){
                    node.nexts[path] = null;
                    return;
                }
            }
            node.end--;
        }

        // word这个单词之前加入过几次
        public int search(String word){
            if (word == null){
                return 0;
            }
            Node node = root;
            char[] str = word.toCharArray();
            int path = 0;
            for (int i = 0; i < str.length; i++){
                path = str[i] - 'a';
                if (node.nexts[path] == null){
                    return 0;
                }
                node = node.nexts[path];
            }
            return node.end;
        }

        // 所有加入的字符串中，有几个是以pre这个字符串作为前缀的
        public int prefixNumber(String pre){
            if (pre == null){
                return 0;
            }
            char[] str = pre.toCharArray();
            Node node = root;
            int path = 0;
            for (int i = 0; i < str.length; i++){
                path = str[i] - 'a';
                if (node.nexts[path] == null){
                    return 0;
                }
                node = node.nexts[path];
            }
            return node.pass;
        }
    }

    public static void main(String[] args) {
        System.out.println('b' - 'a');
    }
}