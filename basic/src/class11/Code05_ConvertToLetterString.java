package class11;

public class Code05_ConvertToLetterString {

    public static int ways(String s){
        if (s == null || s.length() == 0){
            return 0;
        }
        return process(s.toCharArray(), 0);
    }

    // str[0...i-1]已经转化完,不会变
    // i之前的位置,如何转化已经做过决定了,不用再关心
    // i.. 之后有多少种转化的结果
    public static int process(char[] chars, int i){
        // 和之前str[0..i-1]已经转化完了的作为结果返回,结果+1
        if (i == chars.length){
            return 1;
        }
        // i没有到终止位置
        // 前面的转化使得后面碰到一个单独的0会无法转换
        if (chars[i] == '0'){
            return 0;
        }
        // i没有到终止位置
        // str[i]字符不是'0'
        if (chars[i] == '1'){
            int res = process(chars, i + 1); // i自己作为单独的部分，后续有多少种方法
            if (i + 1 < chars.length){
                res += process(chars, i + 2); // (i和i+1)作为单独的部分，后续有多少种方法
            }
            return res;
        }
        if (chars[i] == '2'){
            int res = process(chars, i + 1); // i自己作为单独的部分，后续有多少种方法
            if (i + 1 < chars.length && (chars[i + 1] >= '0' && chars[i + 1] <= '6')){
                res += process(chars, i + 2);// (i和i+1)作为单独的部分，后续有多少种方法
            }
            return res;
        }
        // str[i] = '3'~'9'
        return process(chars, i + 1);
    }

    public static int dpWays(String s){
        if (s == null || s.length() == 0){
            return 0;
        }
        char[] chars = s.toCharArray();
        int n = chars.length;
        int[] dp = new int[n + 1];
        dp[n] = 1;
        for (int index = n - 1; index >= 0; index--){
            if (chars[index] == '0'){
                dp[index] = 0;

            }else if (chars[index] == '1'){
                dp[index] = dp[index + 1];
                if (index + 1 < chars.length){
                    dp[index] += dp[index + 2];
                }

            }else if (chars[index] == '2'){
                dp[index] = dp[index + 1];
                if (index + 1 < chars.length &&
                        (chars[index + 1] >= '0' && chars[index + 1] <= '6')){
                    dp[index] += dp[index + 2];
                }

            }else {
                dp[index] = dp[index + 1];
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        String s = "3211";
        System.out.println(ways(s));
        System.out.println(dpWays(s));
    }
}
