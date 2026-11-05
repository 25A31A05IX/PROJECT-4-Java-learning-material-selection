import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("================================");
        System.out.println(" INCLUSIVE LEARNING MATERIAL");
        System.out.println(" SELECTION SYSTEM");
        System.out.println("================================");

        System.out.print("Enter number of materials: ");
        int n = sc.nextInt();

        int[] cost = new int[n];
        int[] benefit = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\nMaterial " + (i + 1));
            System.out.print("Enter cost: ");
            cost[i] = sc.nextInt();

            System.out.print("Enter accessibility benefit: ");
            benefit[i] = sc.nextInt();
        }

        System.out.print("\nEnter total budget: ");
        int budget = sc.nextInt();

        int[][] dp = new int[n + 1][budget + 1];

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

        System.out.println("\nMaximum Accessibility Benefit: "
                           + dp[n][budget]);

        System.out.println("\nSelected Materials:");
        int w = budget;

        for (int i = n; i > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                System.out.println("Material " + i
                    + " | Cost: " + cost[i - 1]
                    + " | Benefit: " + benefit[i - 1]);
                w -= cost[i - 1];
            }
        }

        System.out.println("\nProject completed successfully!");
        sc.close();
    }
}
