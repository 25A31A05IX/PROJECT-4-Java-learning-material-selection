import java.util.Arrays;
import java.util.Comparator;

public class Greedy {

    public static void solve(int[] cost, int[] benefit, int budget) {

        int n = cost.length;
        Integer[] items = new Integer[n];

        for (int i = 0; i < n; i++) {
            items[i] = i;
        }

        Arrays.sort(items, (a, b) ->
            Double.compare(
                (double) benefit[b] / cost[b],
                (double) benefit[a] / cost[a]
            )
        );

        int totalCost = 0;
        int totalBenefit = 0;

        System.out.println("\n--- Greedy Approach ---");
        System.out.println("Selected Materials:");

        for (int i : items) {
            if (cost[i] <= budget - totalCost) {
                totalCost += cost[i];
                totalBenefit += benefit[i];

                System.out.println(
                    "Material " + (i + 1) +
                    " | Cost: " + cost[i] +
                    " | Benefit: " + benefit[i]
                );
            }
        }

        System.out.println("Total Cost: " + totalCost);
        System.out.println("Total Benefit: " + totalBenefit);
    }
}
