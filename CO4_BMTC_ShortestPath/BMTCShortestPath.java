package CO4_BMTC_ShortestPath;

import java.util.*;

class Edge {
    int u, v, weight;

    Edge(int u, int v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }
}

public class BMTCShortestPath {

    static int[] bellmanFord(int n, List<Edge> edges, int source) {

        int[] dist = new int[n];

        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[source] = 0;

        for (int iter = 0; iter < n - 1; iter++) {

            for (Edge e : edges) {

                if (dist[e.u] != Integer.MAX_VALUE &&
                        dist[e.u] + e.weight < dist[e.v]) {

                    dist[e.v] = dist[e.u] + e.weight;
                }
            }
        }

        for (Edge e : edges) {

            if (dist[e.u] != Integer.MAX_VALUE &&
                    dist[e.u] + e.weight < dist[e.v]) {

                throw new RuntimeException(
                        "Negative cycle reachable from source");
            }
        }

        return dist;
    }

    public static void main(String[] args) {

        String[] hubs = {
                "MJC", "KEM", "JAY",
                "KOR", "WHF", "HBR", "MRT"
        };

        int V = 7;

        List<Edge> edges = new ArrayList<>();

        edges.add(new Edge(0, 1, 8));
        edges.add(new Edge(0, 2, 5));
        edges.add(new Edge(1, 3, 12));
        edges.add(new Edge(2, 3, 7));
        edges.add(new Edge(2, 5, 10));
        edges.add(new Edge(3, 4, 4));
        edges.add(new Edge(3, 6, 9));
        edges.add(new Edge(4, 6, -3));
        edges.add(new Edge(5, 4, 6));
        edges.add(new Edge(5, 6, 11));

        int source = 0;

        int[] dist = bellmanFord(V, edges, source);

        System.out.println("Shortest Travel Time from MJC:");

        for (int i = 0; i < V; i++) {

            System.out.println(
                    hubs[i] + " = " + dist[i] + " min"
            );
        }
    }
}
