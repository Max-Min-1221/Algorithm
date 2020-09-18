package class11;

public class Code07_CardsInLine {

    public static int maxGrade(int[] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }
        return Math.max(
                f(arr, 0, arr.length - 1),
                s(arr, 0 , arr.length - 1)
        );
    }

    // 先手函数
    public static int f(int[] arr, int L, int R){
        if (L == R){
            return arr[L];
        }
        return Math.max(
                arr[L] + s(arr, L + 1, R),
                arr[R] + s(arr, L, R - 1)
        );
    }

    // 后手函数
    public static int s(int[] arr, int L, int R){
        if (L == R){
            return 0;
        }
        return Math.min(
                // 对手拿牌,剩下的分数
                f(arr, L + 1, R),
                f(arr, L, R - 1)
        );
    }

    public static int dpWays(int[] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }
        int n = arr.length;
        int[][] f = new int[n][n];
        int[][] s = new int[n][n];
        // 先填两个数组中主对角线的元素(由暴力递归过程的base case可知)
        for (int i = 0; i < n; i++){
            f[i][i] = arr[i];
        }
        // 填其他元素
        // 右下方移动
        for (int i = 1; i < n; i++){
            int row = 0;
            int col = i;
            while (row < n && col < n){
                f[row][col] = Math.max(
                        arr[row] + s[row + 1][col],
                        arr[col] + s[row][col - 1]
                );
                s[row][col] = Math.min(
                        // 对手拿牌,剩下的分数
                        f[row + 1][col],
                        f[row][col - 1]
                );
                row++;
                col++;
            }
        }
        return Math.max(f[0][arr.length - 1], s[0][arr.length - 1]);
    }

    public static void main(String[] args) {
        int[] arr = { 4,7,9,5,19,29,80,4 };
        System.out.println(maxGrade(arr));
        System.out.println(dpWays(arr));
    }
}
