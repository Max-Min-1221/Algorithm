package class07;

public class Code08_PaperFolding {

    public static void printAllFolds(int N){
        printProcess(1, N, true);
    }

    // i: 当前节点的层数  N: 总的层数
    // down == true: 凹
    public static void printProcess(int i, int N, boolean down){
        if (i > N){
            return;
        }
        printProcess(i + 1, N, true);
        System.out.println(down ? "凹" : "凸");
        printProcess(i + 1, N, false);
    }

    public static void main(String[] args) {
        printAllFolds(3);
    }
}
