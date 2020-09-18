package class11;

public class Code06_Knapsack {

    public static int maxValue(int[] w, int[] v, int bag){
        return process(w, v, 0, bag);
    }

    // rest：剩余空间
    // index... 之后的货物自由选择，但是剩余空间不要小于0
    // 返回 index... 之后货物能够获得的最大价值
    public static int process(int[] w, int[] v, int index, int rest){
        if (rest < 0){
            return -1; // 标记该方案无效
        }
        if (index == w.length){
            return 0;
        }
        // 有货也有空间
        int p1 = process(w, v, index + 1, rest);// 没有算上index位置上的货物
        // 先假设p2是无效方案
        int p2 = -1;
        int p2Next = process(w, v, index + 1, rest - w[index]);// 算上index位置上的货物
        // 说明方案有效
        if (p2Next != -1){
            p2 = v[index] + p2Next;
        }
        return Math.max(p1, p2);
    }

    public static int dpWays(int[] w, int[] v, int bag){
        int n = w.length;
        int[][] dp = new int[n + 1][bag + 1];
        for (int index = n - 1; index >= 0; index--){
            for (int rest = 0; rest <= bag; rest++){
                int p1 = dp[index + 1][rest];
                int p2 = -1;
                // 判断这个后续是否有效
                if (rest - w[index] >= 0){
                    p2 = v[index] + dp[index + 1][rest - w[index]];
                }
                dp[index][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][bag];
    }

    public static void main(String[] args) {
        int[] weights = { 3, 2, 4, 7 };
        int[] values = { 5, 6, 3, 19 };
        int bag = 11;
        System.out.println(maxValue(weights, values, bag));
        System.out.println(dpWays(weights, values, bag));
    }
}
