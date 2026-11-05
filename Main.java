import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("======================================");
        System.out.println(" INCLUSIVE LEARNING MATERIAL SELECTION");
        System.out.println("======================================");

        System.out.print("Enter number of materials: ");
        int n = sc.nextInt();

        if (n <= 0) {
            System.out.println("Invalid number of materials!");
            sc.close();
            return;
        }

        int[] cost = new int[n];
        int[] benefit = new int[n];

        for (int i = 0; i < n; i++) {

            System.out.println("\nMaterial " + (i + 1));

            System.out.print("Enter cost: ");
            cost[i] = sc.nextInt();

            System.out.print("Enter accessibility benefit: ");
            benefit[i] = sc.nextInt();

            if (cost[i] <= 0 || benefit[i] < 0) {
                System.out.println("Invalid cost or benefit!");
                sc.close();
                return;
            }
        }

        System.out.print("\nEnter total budget: ");
        int budget = sc.nextInt();

        if (budget < 0) {
            System.out.println("Budget cannot be negative!");
            sc.close();
            return;
        }

        // Dynamic Programming
        int dpResult = Knapsack.solve(cost, benefit, budget);

        // Greedy Comparison
        Greedy.solve(cost, benefit, budget);

        System.out.println("\n======================================");
        System.out.println(" PROJECT COMPARISON");
        System.out.println("======================================");
        System.out.println("DP Maximum Benefit: " + dpResult);
        System.out.println("Greedy is a heuristic approach.");
        System.out.println("DP gives the optimal 0/1 Knapsack result.");
        System.out.println("======================================");

        sc.close();
    }
}

