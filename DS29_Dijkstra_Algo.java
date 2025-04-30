package Data_Structures;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class DS29_Dijkstra_Algo {
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
        for(int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<Edge>();
        }

        graph[0].add(new Edge(0, 1, 2));
        graph[0].add(new Edge(0, 2, 4));

        graph[1].add(new Edge(1, 3, 7));
        graph[1].add(new Edge(1, 2, 1));

        graph[2].add(new Edge(2, 4, 3));

        graph[3].add(new Edge(3, 5, 1));

        graph[4].add(new Edge(4, 3, 2));
        graph[4].add(new Edge(4, 5, 5));
    }

    //Pair class for storing dist(path) till that node from src
    static class Pair implements Comparable<Pair> {
        int n;
        int path;
        public Pair(int n, int path) {
            this.n = n;
            this.path = path;
        }

        @Override
        public int compareTo(Pair p2) {
            return this.path - p2.path;
        }
    }

    public static int[] dijkstra(ArrayList<Edge> graph[], int src) {
        PriorityQueue<Pair> pq = new PriorityQueue<>(); //it will short the pairs based on path length
        int dist[] = new int[graph.length];
        //initializing the distance of all nodes to infinity except src node
        for(int i = 0; i < dist.length; i++) {
            if(i != src) {
                dist[i] = Integer.MAX_VALUE;
            }
        }

        boolean vis[] = new boolean[graph.length];
        pq.add(new Pair(src, 0));

        while(!pq.isEmpty()) {
            Pair curr = pq.remove(); //the pair with min path length
            if(!vis[curr.n]) {
                vis[curr.n] = true;
                //iterating over all the edges of the current node
                for(int i = 0; i < graph[curr.n].size(); i++) {
                    Edge e = graph[curr.n].get(i);
                    int u = e.src;
                    int v = e.dest;
                    //if the node is not visited and the path length is less than the current path length
                    //then update the path length and add it to the priority queue
                    if(!vis[v] && dist[u]+e.wt < dist[v]) {
                        dist[v] = dist[u] + e.wt;
                        pq.add(new Pair(v, dist[v]));
                    }
                }
            }
        }

        return dist;
    }

    public static void main(String args[]) {
        int V = 6;
        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);

        int src = 0;
        System.out.println("Dijkstra's Algorithm:");
        int dist[] = dijkstra(graph, src);
        // Print distances from source to all nodes
        for(int i = 0; i < dist.length; i++) {
            System.out.print(dist[i] + " ");
        }
        System.out.println();
        
    }

}
