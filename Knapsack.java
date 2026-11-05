public class Knapsack {

    public static int solve(int[] cost, int[] benefit, int budget) {

        int n = cost.length;
        int[][] dp = new int[n + 1][budget + 1];

        // Build the DP table
        for (int i = 1; i <= n; i++) {

            for (int w = 0; w <= budget; w++) {

                if (cost[i - 1] <= w) {

                    dp[i][w] = Math.max(
                        benefit[i - 1] + dp[i - 1][w - cost[i - 1]],
                        dp[i - 1][w]
                    );

                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        System.out.println("\n--- Dynamic Programming Approach ---");
        System.out.println("Selected Materials:");

        int w = budget;

        for (int i = n; i > 0; i--) {

            if (dp[i][w] != dp[i - 1][w]) {

                System.out.println(
                    "Material " + i +
                    " | Cost: " + cost[i - 1] +
                    " | Benefit: " + benefit[i - 1]
                );

                w -= cost[i - 1];
            }
        }

        System.out.println("Total Cost: " + (budget - w));
        System.out.println("Maximum Benefit: " + dp[n][budget]);

        return dp[n][budget];
    }
}
