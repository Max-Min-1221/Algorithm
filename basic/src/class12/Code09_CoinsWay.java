package class12;

public class Code09_CoinsWay {

    public static int ways1(int[] arr, int aim){
        if (arr == null || arr.length == 0){
            return 0;
        }
        return process1(arr, 0, aim);
    }

    // 可以自由使用arr[index...]所有的面值，每一个面值都可以使用任意张
    // 组成rest，有多少种方法
    public static int process1(int[] arr, int index, int rest){
        /*
        if (rest < 0){ 因为下面的循环过程保证了rest不会小于0,所以这个判断可以省略
            return 0;
        }
        */
        // 没有货币可以选择了,到头了
        if (index == arr.length){
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        // 当前有货币,并且用循环枚举所有的可能性
        for (int j = 0; j * arr[index] <= rest; j++){
            ways += process1(arr, index + 1, rest - j * arr[index]);
        }
        return ways;
    }

    public static int ways2(int[] arr, int aim){
        if (arr == null || arr.length == 0){
            return 0;
        }
        int n = arr.length;
        int[][] dp = new int[n + 1][aim + 1];
        // 用傻缓存的方式,先将其中的元素设置成-1
        for (int i = 0; i <= n; i++){
            for (int j = 0; j <= aim; j++){
                dp[i][j] = -1;
            }
        }
        // 每次递归都将dp数组带上
        return process2(arr, 0, aim, dp);
    }

    public static int process2(int[] arr, int index, int rest, int[][] dp){
        if (dp[index][rest] != -1){
            return dp[index][rest];
        }
        if (index == arr.length){
            dp[index][rest] = rest == 0 ? 1 : 0;
            return dp[index][rest];
        }
        int ways = 0;
        for (int j = 0; j * arr[index] <= rest; j++){
            ways += process2(arr, index + 1, rest - j * arr[index], dp);
        }
        dp[index][rest] = ways;
        return ways;
    }

    public static int ways3(int[] arr, int aim){
        if (arr == null || arr.length == 0){
            return 0;
        }
        int n = arr.length;
        int[][] dp = new int[n + 1][aim + 1];
        dp[n][0] = 1;
        // 遍历整个二维数组,填满
        // 由暴力递归方式可知,index位置上的元素要依赖index + 1位置上的元素
        for (int index = n - 1; index >= 0; index--){
            for (int rest = 0; rest <= aim; rest++){
                for (int j = 0; j * arr[index] <= rest; j++){
                    dp[index][rest] += dp[index + 1][rest - j * arr[index]];
                }
            }
        }
        return dp[0][aim];
    }

    public static void main(String[] args) {
        int[] arr = { 5, 10,50,100 };
        int aim = 1000;
        System.out.println(ways1(arr, aim));
        System.out.println(ways2(arr, aim));
        System.out.println(ways3(arr, aim));
    }
}