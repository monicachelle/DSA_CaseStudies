package CO3_BangolreMetro_Krushal;

import java.util.*;

class Edge implements Comparable<Edge> {

    int src, dest, weight;

    Edge(int s, int d, int w) {
        src = s;
        dest = d;
        weight = w;
    }

    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}

public class BangaloreMetroKruskal {

    int[] parent;

    // Find
    int find(int i) {

        if (parent[i] == i)
            return i;

        return find(parent[i]);
    }

    // Union
    void union(int x, int y) {

        int xset = find(x);
        int yset = find(y);

        parent[xset] = yset;
    }

    // Kruskal Algorithm
    void kruskal(ArrayList<Edge> edges, int vertices) {

        Collections.sort(edges);

        parent = new int[vertices];

        for (int i = 0; i < vertices; i++)
            parent[i] = i;

        int totalCost = 0;

        System.out.println("\nSelected Edges in MST:");

        for (Edge edge : edges) {

            int x = find(edge.src);
            int y = find(edge.dest);

            if (x != y) {

                System.out.println(
                        edge.src + " - " + edge.dest +
                        " : Cost = " + edge.weight);

                totalCost += edge.weight;

                union(x, y);
            }
        }

        System.out.println("\nMinimum Total Construction Cost = ₹ " + totalCost + " crore");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of vertices:");
        int vertices = sc.nextInt();

        System.out.println("Enter number of edges:");
        int edgesCount = sc.nextInt();

        ArrayList<Edge> edges = new ArrayList<>();

        System.out.println("Enter edges (source destination cost):");

        for (int i = 0; i < edgesCount; i++) {

            int src = sc.nextInt();
            int dest = sc.nextInt();
            int weight = sc.nextInt();

            edges.add(new Edge(src, dest, weight));
        }

        BangaloreMetroKruskal graph = new BangaloreMetroKruskal();

        graph.kruskal(edges, vertices);

        sc.close();
    }
}
