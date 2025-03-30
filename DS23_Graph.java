package Data_Structures;

import java.util.*;

//Graph implementation using Adjacency List 
//(in that we are using array of arrayLists)...
public class DS23_Graph {
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
    
    public static void main(String[] args) {
        int V = 6;
        ArrayList<Edge> graph[] = new ArrayList[V];

        //creating the graph
        createGraph(graph);

        //neighbours of 2 and their wt
        System.out.println("Neighbours of 2 and their weight : ");
        for(int i = 0; i < graph[2].size(); i++){
            Edge e = graph[2].get(i);
            System.out.println("Neighbour " + e.dest + " with Weight " + e.wt);
        }

        //neighbours of 5 and their wt
        System.out.println("Neighbours of 5 and their weight : ");
        for(int i = 0; i < graph[5].size(); i++){
            Edge e = graph[5].get(i);
            System.out.println("Neighbour " + e.dest + " with Weight " + e.wt);
        }
    }
}
