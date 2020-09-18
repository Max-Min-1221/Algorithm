package class12;

public class Test {

    public static int number(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process(str.toCharArray(), 0);
    }

    // str[0...i-1]已经转化完了，固定了
    // i之前的位置，如何转化已经做过决定了, 不用再关心
    // i... 有多少种转化的结果
    public static int process(char[] str, int i) {
        // 和之前str[0..i-1]已经转化完了的作为结果返回，结果+1
        if (i == str.length) { // base case
            return 1;
        }
        // i没有到终止位置
        // 前面的转化使得后面碰到一个单独的0会无法转换
        if (str[i] == '0') {
            return 0;
        }
        // i没有到终止位置
        // str[i]字符不是'0'
        if (str[i] == '1') {
            int res = process(str, i + 1); // i自己作为单独的部分，后续有多少种方法
            if (i + 1 < str.length) {
                res += process(str, i + 2); // (i和i+1)作为单独的部分，后续有多少种方法
            }
            return res;
        }
        if (str[i] == '2') {
            int res = process(str, i + 1); // i自己作为单独的部分，后续有多少种方法
            if (i + 1 < str.length && (str[i + 1] >= '0' && str[i + 1] <= '6')) {
                res += process(str, i + 2); // (i和i+1)作为单独的部分，后续有多少种方法
            }
            return res;
        }
        // str[i] = '3'~'9'
        return process(str, i + 1);
    }

    public static int dpWays2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[] dp = new int[N+1];
        dp[N] = 1;
        for(int i = N-1; i >= 0; i--) {
            if (str[i] == '0') {
                dp[i] = 0;
            }
            if (str[i] == '1') {
                dp[i] = dp[i + 1];
                if (i + 1 < str.length) {
                    dp[i] += dp[i + 2];
                }
            }
            if (str[i] == '2') {
                dp[i] = dp[i + 1];
                if (i + 1 < str.length && (str[i + 1] >= '0' && str[i + 1] <= '6')) {
                    dp[i] += dp[i + 2]; // (i和i+1)作为单独的部分，后续有多少种方法
                }
            }
        }
        return dp[0];
    }


    public static int dpWays(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int i = N - 1; i >= 0; i--) {
            if (str[i] == '0') {
                dp[i] = 0;
            } else if (str[i] == '1') {
                dp[i] = dp[i + 1];
                if (i + 1 < N) {
                    dp[i] += dp[i + 2];
                }
            } else if (str[i] == '2') {
                dp[i] = dp[i + 1];
                if (i + 1 < str.length && (str[i + 1] >= '0' && str[i + 1] <= '6')) {
                    dp[i] += dp[i + 2];
                }
            } else {
                dp[i] = dp[i + 1];
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        System.out.println(number("3211"));
        System.out.println(dpWays("3211"));
    }

}
