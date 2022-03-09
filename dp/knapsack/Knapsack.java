package knapsack;

public class Knapsack {
    public static void main(String[] args) {
        weights = new int[] {3,4,1,2,3};
        prices = new int[] {2,3,2,3,6};

        for (int i = 0; i < dp.length; i++) {
            for (int w = 0; w < dp[0].length; w++) {
                dp[i][w] = -1;
            }
        }

        System.out.println(knapsack(0,0));
    }

    private static int knapsack(int idx, int wsum) {
        if (idx == weights.length) {
            return 0;
        }

        if (dp[idx][wsum] != -1) return dp[idx][wsum];

        dp[idx][wsum] = 0;
        int nowWeight = weights[idx];
        int nowValue = prices[idx];

        // 1. pick
        if (wsum + nowWeight <= WEIGHT_LIMIT) {
            dp[idx][wsum] = Math.max(dp[idx][wsum], knapsack(idx + 1, wsum + nowWeight) + nowValue);
        }

        // 2. not pick
        dp[idx][wsum] = Math.max(dp[idx][wsum], knapsack(idx + 1, wsum));

        return dp[idx][wsum];
    }

    private static int[] weights, prices;
    private static final int WEIGHT_LIMIT = 10;
    private static int[][] dp = new int[10][WEIGHT_LIMIT+1];
}
