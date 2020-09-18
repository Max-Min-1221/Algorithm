package class11;

public class Code08_NQueens {

    public static int num(int n){
        if (n < 1){
            return 0;
        }
        int[] record = new int[n]; // record[i] -> i行的皇后,放在了第几列
        return process(0, record);
    }

    // record[0...i-1]的皇后,任何两个皇后一定不共行,不共列,不在同一条斜线上
    // i: 目前来到第i行,要在第i行某一列放皇后
    // record[0..i-1]表示之前的行,放了皇后的位置 -> i行,j列
    // 返回值: 摆完所有的皇后,合理的摆法有多少
    public static int process(int i, int[] record){
        if (i == record.length){ // 终止行
            return 1;
        }
        // 没有到终止位置,还有皇后要放
        int res = 0;
        for (int j = 0; j < record.length; j++){ // 当前行在i行,尝试i行所有的列 -> j
            // 当前i行的皇后,放在j列的话,会不会和之前(0, i-1)的皇后，不共行共列或者共斜线
            // true: 有效
            // false: 无效
            if (isValid(record, i, j)){
                record[i] = j; // 用完之后要设置上
                res += process(i + 1, record);
            }
        }
        return res;
    }

    // 返回第i行皇后放在了j列,这种放法是否有效
    public static boolean isValid(int[] record, int i, int j){
        for (int k = 0; k < i; k++){ // 在i之前的所有行,遍历判断是否有效
            // k, record[k]  --> i, j
            if (j == record[k] || Math.abs(k - i) == Math.abs(record[k] - j)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(num(13));
    }
}
