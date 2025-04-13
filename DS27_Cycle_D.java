package Data_Structures;

import java.util.ArrayList;

public class DS27_Cycle_D {
    static class Edge {
        int src;
        int dest;
        public Edge(int s, int d) {
            this.src = s;
            this.dest = d;
        }
    }

    //graph 1 -> true
    static void createGraph(ArrayList<Edge> graph[]) {
        for(int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        graph[0].add(new Edge(0, 2));
        graph[1].add(new Edge(1, 0));
        graph[2].add(new Edge(2, 3));
        graph[3].add(new Edge(3, 0));
    }

    //graph 2 -> false
    /*
    static void createGraph(ArrayList<Edge> graph[]) {
        for(int i=0; i<graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        graph[0].add(new Edge(0, 1));
        graph[0].add(new Edge(0, 2));
        graph[1].add(new Edge(1, 3));
        graph[2].add(new Edge(2, 3));
    }
    */

    public static boolean isCyclicUtil(ArrayList<Edge>[] graph, boolean vis[], int curr, boolean stack[]) {
        vis[curr] = true;
        stack[curr] = true;

        for(int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if(stack[e.dest]){ 
                return true;
            }
            else if(!vis[e.dest] && isCyclicUtil(graph, vis, e.dest, stack)){
                return true;
            }
        }

        stack[curr] = false;
        return false;
    }

    //O(V+E)
    public static boolean isCyclic(ArrayList<Edge>[] graph, boolean vis[]) {
        for(int i = 0; i < graph.length; i++) {
            if(isCyclicUtil(graph, vis, i, new boolean[vis.length])) {
                return true;
            }
        }
        return false;
    }


    public static void main(String args[]) {
        int V = 5;
        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);
        System.out.println(isCyclic(graph, new boolean[V]));
    }
}
