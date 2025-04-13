package Data_Structures;

import java.util.*;

public class DS26_CycleDetection_unD {
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
        graph[0].add(new Edge(0, 1));
        graph[0].add(new Edge(0, 2));
        graph[0].add(new Edge(0, 3));
        graph[1].add(new Edge(1, 0));
        graph[1].add(new Edge(1, 2));
        graph[2].add(new Edge(2, 0));
        graph[2].add(new Edge(2, 1));
        graph[3].add(new Edge(3, 0));
        graph[3].add(new Edge(3, 4));
        graph[4].add(new Edge(4, 3));
    }

    public static boolean isCyclicUtil(ArrayList<Edge>[] graph, boolean vis[], int curr, int par) {
        vis[curr] = true;
        for(int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            //case1
            if(vis[e.dest] && e.dest != par) {
                boolean isCycle = isCyclicUtil(graph, vis, e.dest, curr);
                if(isCycle){
                    return true;
                }
            }
            //case 2
            else if(e.dest == par){
                continue;
            }
            //case 3
            else{
                return true;
            }
        }

        return false;
    }

    //O(V+E)
    public static boolean isCyclic(ArrayList<Edge>[] graph, boolean vis[]) {
        for(int i = 0; i < graph.length; i++) {
            if(isCyclicUtil(graph, vis, i, -1)) {
                return true;
            }
        }
        return false;
    }


    public static void main(String args[]) {
        /*
               0 ------- 3
              /|         |
             / |         |
            1  |         4
             \ |
              \|
               2
        */
        int V = 5;
        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);
        System.out.println(isCyclic(graph, new boolean[V]));

    }
}