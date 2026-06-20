package CO6_Knapsack_Logistics;
import java.util.*;

public class KnapsackLogistics {

    static List<Integer> knapsack01(int[] weights, int[] values, int W) {

        int n = weights.length;

        int[][] dp = new int[n + 1][W + 1];

        // Build DP Table
        for (int i = 1; i <= n; i++) {

            for (int w = 0; w <= W; w++) {

                // Skip item
                dp[i][w] = dp[i - 1][w];

                // Take item if possible
                if (weights[i - 1] <= w) {

                    dp[i][w] = Math.max(
                            dp[i][w],
                            dp[i - 1][w - weights[i - 1]]
                                    + values[i - 1]
                    );
                }
            }
        }

        System.out.println("Maximum Value = ₹" + dp[n][W] + "k");

        // Recover selected items
        List<Integer> chosen = new ArrayList<>();

        int w = W;

        for (int i = n; i >= 1; i--) {

            if (dp[i][w] != dp[i - 1][w]) {

                chosen.add(i);

                w -= weights[i - 1];
            }
        }

        Collections.reverse(chosen);

        return chosen;
    }

    public static void main(String[] args) {

        String[] itemNames = {
                "A","B","C","D","E","F","G","H"
        };

        int[] weights = {
                5, 8, 3, 10, 4, 6, 7, 2
        };

        int[] values = {
                40, 50, 20, 70, 30, 35, 45, 15
        };

        int capacity = 24;

        List<Integer> selected =
                knapsack01(weights, values, capacity);

        int totalWeight = 0;
        int totalValue = 0;

        System.out.println("\nSelected Consignments:");

        for (int index : selected) {

            int i = index - 1;

            System.out.println(
                    itemNames[i]
                            + " (Weight="
                            + weights[i]
                            + "t, Value=₹"
                            + values[i]
                            + "k)"
            );

            totalWeight += weights[i];
            totalValue += values[i];
        }

        System.out.println("\nTotal Weight = "
                + totalWeight + " tons");

        System.out.println("Total Value = ₹"
                + totalValue + "k");
    }
}
