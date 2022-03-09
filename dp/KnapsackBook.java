public class KnapsackBook {
    public static void main(String[] args) {
        weights = new int[]{3, 4, 1, 2, 3};
        prices = new int[]{2, 3, 2, 3, 6};

        for (int i = 0; i < dp.length; i++) {
            for (int w = 0; w < dp[0].length; w++) {
                dp[i][w] = -1;
            }
        }

        System.out.println(knapsack(0, 0));
    }

    private static int knapsack(int idx, int wsum) {
        if (wsum > WEIGHT_LIMIT) return Integer.MIN_VALUE;  //책에는 -1을 리턴하던데 그러면 정상 동작 안 하지 -MAX를 리턴해야지
        if (idx >= weights.length) return 0;
        if (dp[idx][wsum] >= 0) return dp[idx][wsum];
        return dp[idx][wsum] = Math.max(knapsack(idx + 1, wsum), knapsack(idx + 1, wsum + weights[idx]) + prices[idx]);
    }

    private static int[] weights, prices;
    private static final int WEIGHT_LIMIT = 10;
    private static int[][] dp = new int[10][WEIGHT_LIMIT + 1];

}
