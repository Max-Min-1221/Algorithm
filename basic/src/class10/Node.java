package class10;

import java.util.ArrayList;

public class Node {

    public int value;
    public int in;
    public int out;
    public ArrayList<Node> nexts;
    public ArrayList<Edge> edges;

    // 初始化时都为空
    public Node(int value){
        this.value =  value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
