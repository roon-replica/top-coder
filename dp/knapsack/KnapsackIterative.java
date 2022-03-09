package knapsack;

public class KnapsackIterative {
    public static void main(String[] args) {
        weights = new int[]{3, 4, 1, 2, 3};
        values = new int[]{2, 3, 2, 3, 6};

        // O( weights length * weights range)
        for (int here = 0; here < weights.length; here++) {
            int nxt = here+1;
            int hereWeight = weights[here];
            int hereValue = values[here];

            for (int w = 0; w <= WEIGHT_LIMIT; w++) {
                if (hereWeight + w <= WEIGHT_LIMIT) {

                    // 1. pick
                    dp[nxt][hereWeight + w] = Math.max(dp[nxt][hereWeight + w], dp[here][w] + hereValue);
                }

                // 2. not pick
                dp[nxt][w] = Math.max(dp[nxt][w], dp[here][w]);     // 이 코드 필요없는 듯? 이렇게 명시적으로 안해도 1.pick에서 언젠가 값이 채워지는 듯? w가 0~WEIGHT_LIMIT까지 다 반복하니까
            }
        }

        int maxValueSum = 0;
        for (int w = 0; w <= WEIGHT_LIMIT; w++) {
            maxValueSum = Math.max(maxValueSum, dp[weights.length][w]);
        }

        System.out.println(maxValueSum);


    }

    private static int[] weights, values;
    private static final int WEIGHT_LIMIT = 10;
    private static int[][] dp = new int[10][WEIGHT_LIMIT + 1];
}
