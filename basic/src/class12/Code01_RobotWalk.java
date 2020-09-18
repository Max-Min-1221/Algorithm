package class12;

public class Code01_RobotWalk {

    public static int ways(int N, int M, int K, int P){
        if (N < 1 || M < 1 || M > N || P < 1 || P > N){
            return 0;
        }
        return walk(N, M, K, P);
    }

    /**
     * @param N 位置为1-N,固定参数
     * @param cur 当前在cur位置,可变参数
     * @param rest 剩余rest步没有走,可变参数
     * @param P 最终目标位置,可变参数
     * @return 只能在1-N这些位置移动,当前在cur位置,走完rest步之后,能停在P位置的方法数
     */
    public static int walk(int N, int cur, int rest, int P){
        // 如果没有步数可走了,当前的位置cur就是最后的位置
        // 如果停在了P上,那么之前做的移动是有效的,返回1,意味着这是一种有效结果
        // 如果没有停在P上,那么之前做的移动是无效的,返回0,意味着这是一种无效结果
        if (rest == 0){
            return cur == P ? 1 : 0;
        }
        // rest还有步数可以走,如果当前位置来到了N(最右),下一步必须往左走
        // 后续的过程就是,来到N-1位置上,还剩rest-1步要走
        if (cur == N){
            return walk(N, cur - 1, rest - 1, P);
        }
        // rest还有步数可以走,如果当前位置来到了1(最左),下一步必须往右走
        // 后续的过程就是,来到2位置上,还剩rest-1步要走
        if (cur == 1){
            return walk(N, cur + 1, rest - 1, P);
        }
        // rest还有步数可以走,并且当前位置在中间位置,那么既可以往左走,也可以往右走
        // 往左往右是两种不同的方法,所以需要全部加上
        return walk(N, cur + 1, rest - 1, P)
                + walk(N, cur - 1, rest - 1, P);
    }

    public static int waysCache(int N, int M, int K, int P){
        if (N < 1 || M < 1 || M > N || P < 1 || P > N){
            return 0;
        }
        // 准备一个缓存表
        int[][] dp = new int[N + 1][K + 1];
        // 设置表: -1表示还没有算过
        for (int row = 0; row <= N; row++){
            for (int col = 0; col <= K; col++){
                dp[row][col] = -1;
            }
        }
        return walkCache(N, M, K, P, dp);
    }

    // 把所有的cur和rest的组合,返回的结果,加入到缓存中
    public static int walkCache(int N, int cur, int rest, int P, int[][] dp){
        if (dp[cur][rest] != -1){
            return dp[cur][rest];
        }
        if (rest == 0){
            dp[cur][rest] = cur == P ? 1 :0;
            return dp[cur][rest];
        }
        if (cur == N){
            dp[cur][rest] =  walkCache(N, cur - 1, rest - 1, P, dp);
            return dp[cur][rest];
        }
        if (cur == 1){
            dp[cur][rest] = walkCache(N, cur + 1, rest - 1, P, dp);
            return dp[cur][rest];
        }
        dp[cur][rest] = walkCache(N, cur + 1, rest - 1, P, dp)
                + walkCache(N, cur - 1, rest - 1, P, dp);
        return dp[cur][rest];
    }

    // 经典动态规划
    public static int ways2(int N, int M, int K, int P){
        int[][] dp = new int[N + 1][K + 1];
        dp[P][0] = 1;
        for (int i = 1; i <= N; i++){
            for (int j = 1; j <= K; j++){
                if (i == 1){
                    dp[i][j] = dp[i + 1][j - 1];
                }else if (i == N){
                    dp[i][j] = dp[i - 1][j - 1];
                }else {
                    dp[i][j] = dp[i + 1][j - 1] + dp[i - 1][j - 1];
                }
            }
        }
        return dp[M][K];
    }

    public static void main(String[] args) {
        System.out.println(ways(7, 4, 9, 5));
        System.out.println(waysCache(7, 4, 9, 5));
        System.out.println(ways2(7, 4, 9, 5));
    }
}