package Data_Structures;

import java.util.ArrayList;
import java.util.Stack;

public class DS28_TopologicalSort {
    static class Edge {
        int src;
        int dest;
        public Edge(int s, int d) {
            this.src = s;
            this.dest = d;
        }
    }

    static void createGraph(ArrayList<Edge> graph[]) {
        for(int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        graph[2].add(new Edge(2, 3));
        graph[3].add(new Edge(3, 1));
        graph[4].add(new Edge(4, 0));
        graph[4].add(new Edge(4, 1));
        graph[5].add(new Edge(5, 0));
        graph[5].add(new Edge(5, 2));
    }

    public static void topSortUntil(ArrayList<Edge>[] graph, boolean vis[], int curr, Stack<Integer> s) {
        vis[curr] = true;

        for(int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if(!vis[e.dest]){ 
                topSortUntil(graph, vis, e.dest, s);
            }
        }

        s.push(curr);
    }

    //O(V+E)
    public static void topSort(ArrayList<Edge>[] graph) {
        boolean vis[] = new boolean[graph.length];
        Stack<Integer> s = new Stack<>();
        for(int i = 0; i < graph.length; i++) {
            if(!vis[i]){
                topSortUntil(graph, vis, i, s);
            }
        }
        
        while (!s.isEmpty()) {
            System.out.print(s.pop()+" ");
        }
    }


    public static void main(String args[]) {
        int V = 6;
        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);

        topSort(graph);
    }
}
