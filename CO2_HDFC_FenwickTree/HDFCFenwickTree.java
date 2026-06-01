package CO2_HDFC_FenwickTree;

import java.util.Scanner;

public class HDFCFenwickTree {

    int[] BIT;
    int n;

    // Constructor
    HDFCFenwickTree(int size) {
        n = size;
        BIT = new int[n + 1];
    }

    // Update Operation
    void update(int index, int value) {

        while (index <= n) {
            BIT[index] += value;
            index += index & (-index);
        }
    }

    // Prefix Sum
    int prefixSum(int index) {

        int sum = 0;

        while (index > 0) {
            sum += BIT[index];
            index -= index & (-index);
        }

        return sum;
    }

    // Range Sum
    int rangeSum(int left, int right) {
        return prefixSum(right) - prefixSum(left - 1);
    }

    // Display BIT Array
    void displayBIT() {

        System.out.println("\nFenwick Tree (BIT) Array:");

        for (int i = 1; i <= n; i++) {
            System.out.println("BIT[" + i + "] = " + BIT[i]);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of days:");
        int n = sc.nextInt();

        HDFCFenwickTree tree = new HDFCFenwickTree(n);

        int[] spend = new int[n + 1];

        System.out.println("Enter daily spend values:");

        // User Input
        for (int i = 1; i <= n; i++) {

            spend[i] = sc.nextInt();

            tree.update(i, spend[i]);
        }

        // Display BIT Array
        tree.displayBIT();

        // Range Query
        System.out.println("\nEnter left range:");
        int left = sc.nextInt();

        System.out.println("Enter right range:");
        int right = sc.nextInt();

        int result = tree.rangeSum(left, right);

        System.out.println("\nTotal Spend from Day " + left + " to Day " + right + " = ₹ " + result);

        // Point Update
        System.out.println("\nEnter day to update:");
        int day = sc.nextInt();

        System.out.println("Enter amount to add:");
        int amount = sc.nextInt();

        tree.update(day, amount);

        System.out.println("\nBIT Array after Update:");
        tree.displayBIT();

        sc.close();
    }
}