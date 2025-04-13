package Data_Structures;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class DS24_Graph_BFS_DFS {
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
        graph[0].add(new Edge(0, 1, 1));
        graph[0].add(new Edge(0, 2, 1));

        graph[1].add(new Edge(1, 0, 1));
        graph[1].add(new Edge(1, 3, 1));

        graph[2].add(new Edge(2, 0, 1));
        graph[2].add(new Edge(2, 4, 1));

        graph[3].add(new Edge(3, 1, 1));
        graph[3].add(new Edge(3, 4, 1));
        graph[3].add(new Edge(3, 5, 1));

        graph[4].add(new Edge(4, 2, 1));
        graph[4].add(new Edge(4, 3, 1));
        graph[4].add(new Edge(4, 5, 1));

        graph[5].add(new Edge(5, 3, 1));
        graph[5].add(new Edge(5, 4, 1));
        graph[5].add(new Edge(5, 6, 1));
        graph[5].add(new Edge(6, 5, 1));
    }

    //bfs
    public static void bfs(ArrayList<Edge> graph[], int V, boolean visited[], int start){
        Queue<Integer> q = new LinkedList<>();
        q.add(start); //Source = 0

        while(!q.isEmpty()) {
            int curr = q.remove();
            if(!visited[curr]) {
                System.out.print(curr+" ");
                visited[curr] = true;

                for(int i = 0; i < graph[curr].size(); i++){
                    Edge e = graph[curr].get(i);
                    q.add(e.dest);
                }
            }
        }

        System.out.println();
    }

    //dfs
    public static void dfs(ArrayList<Edge> graph[], int curr, boolean visited[]){
        if(visited[curr]) {
            return;
        }

        System.out.print(curr+" ");
        visited[curr] = true;

        for(int i = 0; i < graph[curr].size(); i++){
            Edge e = graph[curr].get(i);
            dfs(graph, e.dest, visited);
        }

    }

    
    public static void main(String[] args) {
        int V = 7;
        ArrayList<Edge> graph[] = new ArrayList[V];

        //creating the graph
        createGraph(graph);

        /* created graph
             1 --- 3
            /      | \
            0      | 5 -- 6
            \      | /
             2 --- 4
        */

        /*
        //bfs if no broken parts
        bfs(graph, V);
        */

        //bfs if broken parts are their
        System.out.println("BFS : ");
        boolean visited[] = new boolean[V];
        for(int i = 0; i < V; i++){
            if(visited[i] == false){
                bfs(graph, V, visited, i);
            }
        }

        //dfs if broken parts are their
        System.out.println("DFS : ");
        boolean visited2[] = new boolean[V];
        for(int i = 0; i < V; i++){
            if(visited2[i] == false){
                dfs(graph, i, visited2);
            }
        }
        System.out.println();
    }
}
