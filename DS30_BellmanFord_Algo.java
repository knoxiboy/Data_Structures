package Data_Structures;

import java.util.ArrayList;

public class DS30_BellmanFord_Algo {
    static class Edge {
        int src;
        int dest;
        int wt;
        public Edge(int s, int d, int w) {
            this.src = s;
            this.dest = d;
            this.wt = w;
        }
    }

    static void createGraph(ArrayList<Edge> graph[]) {
        for(int i=0; i<graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0, 1, 2));
        graph[0].add(new Edge(0, 2, 4));

        graph[1].add(new Edge(1, 2, -4));

        graph[2].add(new Edge(2, 3, 2));

        graph[3].add(new Edge(3, 4, 4));

        graph[4].add(new Edge(4, 1, -1));
    }

    static void bellmanFord(ArrayList<Edge> graph[], int src) {
        int dist[] = new int[graph.length];
        for(int i=0; i<dist.length; i++) {
            if(i != src) {
                dist[i] = Integer.MAX_VALUE;
            }
        }

        // Relaxation
        for(int i=0; i<graph.length-1; i++) {
            for(int j=0; j<graph.length; j++) {
                for(Edge e : graph[j]) {
                    if(dist[e.src] != Integer.MAX_VALUE && dist[e.src] + e.wt < dist[e.dest]) {
                        dist[e.dest] = dist[e.src] + e.wt;
                    }
                }
            }
        }

        // Negative weight cycle detection
        for(int j=0; j<graph.length; j++) {
            for(Edge e : graph[j]) {
                if(dist[e.src] != Integer.MAX_VALUE && dist[e.src] + e.wt < dist[e.dest]) {
                    System.out.println("Negative weight cycle detected");
                    return;
                }
            }
        }

        // Print distances
        for(int i=0; i<dist.length; i++) {
            System.out.print(dist[i] + " ");
        }
    }

    public static void main(String[] args) {
        int V = 5;
        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);

        System.out.println("Bellman-Ford Algorithm:");
        bellmanFord(graph, 0);
    }
}
