import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

    // 1. Dynamic Programming - 0/1 Knapsack
    static int knapsack(String[] names, int[] cost,
                        int[] benefit, int budget) {

        int n = cost.length;
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

        System.out.println("\n===== DYNAMIC PROGRAMMING =====");
        System.out.println("Selected Learning Materials:");

        int w = budget;
        int totalCost = 0;

        for (int i = n; i > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {

                System.out.println(
                    names[i - 1] +
                    " | Cost: " + cost[i - 1] +
                    " | Benefit: " + benefit[i - 1]
                );

                totalCost += cost[i - 1];
                w -= cost[i - 1];
            }
        }

        System.out.println("Total Cost: " + totalCost);
        System.out.println("Maximum Accessibility Benefit: " + dp[n][budget]);

        return dp[n][budget];
    }

    // 2. Greedy Algorithm
    static int greedy(String[] names, int[] cost,
                      int[] benefit, int budget) {

        int n = cost.length;
        Integer[] items = new Integer[n];

        for (int i = 0; i < n; i++) {
            items[i] = i;
        }

        Arrays.sort(items, new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                double ratioA = (double) benefit[a] / cost[a];
                double ratioB = (double) benefit[b] / cost[b];
                return Double.compare(ratioB, ratioA);
            }
        });

        int totalCost = 0;
        int totalBenefit = 0;

        System.out.println("\n===== GREEDY APPROACH =====");
        System.out.println("Selected Learning Materials:");

        for (int i : items) {
            if (cost[i] <= budget - totalCost) {

                totalCost += cost[i];
                totalBenefit += benefit[i];

                System.out.println(
                    names[i] +
                    " | Cost: " + cost[i] +
                    " | Benefit: " + benefit[i]
                );
            }
        }

        System.out.println("Total Cost: " + totalCost);
        System.out.println("Total Benefit: " + totalBenefit);

        return totalBenefit;
    }

    // 3. Brute Force Verification
    static int bruteForce(int[] cost, int[] benefit, int budget) {

        int n = cost.length;
        int maximumBenefit = 0;

        for (int mask = 0; mask < (1 << n); mask++) {

            int totalCost = 0;
            int totalBenefit = 0;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    totalCost += cost[i];
                    totalBenefit += benefit[i];
                }
            }

            if (totalCost <= budget) {
                maximumBenefit = Math.max(
                    maximumBenefit, totalBenefit
                );
            }
        }

        return maximumBenefit;
    }

    // 4. Main Method
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("==============================================");
        System.out.println(" INCLUSIVE LEARNING-MATERIAL SELECTION SYSTEM");
        System.out.println("==============================================");

        System.out.print("Enter number of materials (1-20): ");
        int n = sc.nextInt();
        sc.nextLine();

        if (n <= 0 || n > 20) {
            System.out.println("Invalid number! Enter between 1 and 20.");
            sc.close();
            return;
        }

        String[] names = new String[n];
        int[] cost = new int[n];
        int[] benefit = new int[n];

        // Input material details
        for (int i = 0; i < n; i++) {

            System.out.println("\nMaterial " + (i + 1));

            System.out.print("Enter material name: ");
            names[i] = sc.nextLine().trim();

            if (names[i].isEmpty()) {
                System.out.println("Material name cannot be empty!");
                sc.close();
                return;
            }

            System.out.print("Enter cost: ");
            cost[i] = sc.nextInt();

            System.out.print("Enter accessibility benefit: ");
            benefit[i] = sc.nextInt();
            sc.nextLine();

            if (cost[i] <= 0 || benefit[i] < 0) {
                System.out.println("Invalid cost or benefit!");
                sc.close();
                return;
            }
        }

        // Input total budget
        System.out.print("\nEnter total budget: ");
        int budget = sc.nextInt();

        if (budget < 0 || budget > 100000) {
            System.out.println("Budget must be between 0 and 100000.");
            sc.close();
            return;
        }

        // Run all algorithms
        int dpResult = knapsack(names, cost, benefit, budget);

        int greedyResult = greedy(names, cost, benefit, budget);

        System.out.println("\n===== BRUTE FORCE VERIFICATION =====");

        int bruteResult = bruteForce(cost, benefit, budget);

        System.out.println("Brute Force Maximum Benefit: " + bruteResult);

        if (dpResult == bruteResult) {
            System.out.println("Verification Status: PASSED");
        } else {
            System.out.println("Verification Status: FAILED");
        }

        // Final comparison
        System.out.println("\n===== PROJECT COMPARISON =====");

        System.out.println("Dynamic Programming Benefit: " + dpResult);
        System.out.println("Greedy Approach Benefit: " + greedyResult);
        System.out.println("Brute Force Benefit: " + bruteResult);

        System.out.println("\nDP gives the optimal 0/1 Knapsack solution.");
        System.out.println("Greedy is used as a comparison heuristic.");

        // Complexity analysis
        System.out.println("\n===== COMPLEXITY ANALYSIS =====");

        System.out.println("DP Time Complexity: O(n * W)");
        System.out.println("DP Space Complexity: O(n * W)");
        System.out.println("Greedy Time Complexity: O(n log n)");
        System.out.println("Brute Force Time Complexity: O(2^n)");

        System.out.println("\n==============================================");
        System.out.println(" PROJECT COMPLETED SUCCESSFULLY!");
        System.out.println("==============================================");

        sc.close();
    }
}
