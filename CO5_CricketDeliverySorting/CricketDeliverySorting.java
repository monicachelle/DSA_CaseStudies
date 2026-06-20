package CO5_CricketDeliverySorting;
import java.util.Scanner;

class Delivery {
    int over;
    int ball;

    Delivery(int over, int ball) {
        this.over = over;
        this.ball = ball;
    }
}

public class CricketDeliverySorting {

    // Stable Counting Sort by Ball Number
    static Delivery[] countingSortByBall(Delivery[] arr) {

        int maxBall = 12; // Maximum possible ball number

        int[] count = new int[maxBall + 1];

        for (Delivery d : arr) {
            count[d.ball]++;
        }

        for (int i = 1; i <= maxBall; i++) {
            count[i] += count[i - 1];
        }

        Delivery[] output = new Delivery[arr.length];

        for (int i = arr.length - 1; i >= 0; i--) {
            Delivery d = arr[i];
            output[--count[d.ball]] = d;
        }

        return output;
    }

    // Stable Counting Sort by Over Number
    static Delivery[] countingSortByOver(Delivery[] arr) {

        int maxOver = 49;

        int[] count = new int[maxOver + 1];

        for (Delivery d : arr) {
            count[d.over]++;
        }

        for (int i = 1; i <= maxOver; i++) {
            count[i] += count[i - 1];
        }

        Delivery[] output = new Delivery[arr.length];

        for (int i = arr.length - 1; i >= 0; i--) {
            Delivery d = arr[i];
            output[--count[d.over]] = d;
        }

        return output;
    }

    // Display Deliveries
    static void printDeliveries(Delivery[] arr) {
        for (Delivery d : arr) {
            System.out.print("(" + d.over + "," + d.ball + ") ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of deliveries: ");
        int n = sc.nextInt();

        Delivery[] deliveries = new Delivery[n];

        System.out.println("Enter Over and Ball Number:");

        for (int i = 0; i < n; i++) {
            int over = sc.nextInt();
            int ball = sc.nextInt();

            deliveries[i] = new Delivery(over, ball);
        }

        System.out.println("\nOriginal Order:");
        printDeliveries(deliveries);

        // Pass 1: Sort by Ball
        deliveries = countingSortByBall(deliveries);

        System.out.println("\nAfter Stable Counting Sort by Ball:");
        printDeliveries(deliveries);

        // Pass 2: Sort by Over
        deliveries = countingSortByOver(deliveries);

        System.out.println("\nFinal Sorted Order (Over, Ball):");
        printDeliveries(deliveries);

        sc.close();
    }
}