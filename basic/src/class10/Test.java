package class10;

public class Test {
    public static void main(String[] args) {
        System.out.println(bitCount(1));
    }

    public static int bitCount(int num){
        int count = 0;
        while (num != 0){
            int bit = num % 2;
            if (bit == 0){
                count++;
            }
            num = num / 2;
        }
        return count;
    }
}
