import java.util.LinkedList;

public class AlgoritmFlord {
        static final int V = 5;

        boolean bfs(int[][] rGraph, int s, int t, int[] parent)
        {
            boolean[] visited = new boolean[V];
            for (int i = 0; i < V; ++i)
                visited[i] = false;

            LinkedList<Integer> queue
                    = new LinkedList<Integer>();
            queue.add(s);
            visited[s] = true;
            parent[s] = -1;

            while (queue.size() != 0) {
                int u = queue.poll();

                for (int v = 0; v < V; v++) {
                    if (!visited[v] && rGraph[u][v] > 0) {
                        if (v == t) {
                            parent[v] = u;
                            return true;
                        }
                        queue.add(v);
                        parent[v] = u;
                        visited[v] = true;
                    }
                }
            }
            return false;
        }

        int fordFulkerson(int[][] graph, int s, int t)
        {
            int u, v;

            int[][] rGraph = new int[V][V];

            for (u = 0; u < V; u++)
                for (v = 0; v < V; v++)
                    rGraph[u][v] = graph[u][v];

            int[] parent = new int[V];

            int max_flow = 0;

            while (bfs(rGraph, s, t, parent)) {

                int path_flow = Integer.MAX_VALUE;
                for (v = t; v != s; v = parent[v]) {
                    u = parent[v];
                    path_flow
                            = Math.min(path_flow, rGraph[u][v]);
                }

                for (v = t; v != s; v = parent[v]) {
                    u = parent[v];
                    rGraph[u][v] -= path_flow;
                    rGraph[v][u] += path_flow;
                }

                // Add path flow to overall flow
                max_flow += path_flow;
            }

            return max_flow;
        }

        public static void main(String[] args)
                throws java.lang.Exception
        {
            int[][] graph = new int[][] {
                    { 0, 20, 30, 10, 0 },
                    { 0, 40, 0, 0, 30 } ,
                    { 0, 0, 0, 10, 20 },
                    { 0, 0, 5, 0, 20 },
                    { 0, 0, 0, 0, 0 },
            };
            AlgoritmFlord m = new AlgoritmFlord();

            System.out.println("The maximum possible flow is "
                    + m.fordFulkerson(graph, 0, 4));
        }
    }


