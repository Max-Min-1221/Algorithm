package class11;

public class Code01_Hanota {

    // 汉诺塔问题,N个圆盘至少需要移动多少次
    public static void hanota(int N){
        process(N, "left", "right", "mid");
    }

    public static void process(int num, String from, String to, String other){
        if (num == 1){ // 只剩最后一个圆盘,并且是最高的那一个
            System.out.println("1 from " + from + " to " + to);
        }else {
            // 将N-1个圆盘从from移动到other上
            process(num - 1, from, other, to);
            // 将第N个圆盘从from移动到to上
            System.out.println(num + " from " + from + " to " + to);
            // 将N-1个圆盘从other移动到to上
            process(num - 1, other, to, from);
        }
    }

    public static void main(String[] args) {
        hanota(4);
    }
}
