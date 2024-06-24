public class GroupProjectAlgo {
    static class Graph {
        private int[][] adjMatrix;
        private String[] vertexData;
        private int size;   
        
        public Graph(int size) {
            this.size = size;
            this.adjMatrix = new int[size][size];
            this.vertexData = new String[size];
        }
    
        public void addEdge(int u, int v, int weight) {
            if (0 <= u && u < size && 0 <= v && v < size) {
                adjMatrix[u][v] = weight;
                // Uncomment the line below for an undirected graph
                // adjMatrix[v][u] = weight;
            }
        }
    
        public void addVertexData(int vertex, String data) {
            if (0 <= vertex && vertex < size) {
                vertexData[vertex] = data;
            }
        }
    
        public int[] dijkstra(String startVertexData) {
            int startVertex = findVertexIndex(startVertexData);
            int[] distances = new int[size];
            boolean[] visited = new boolean[size];
    
            for (int i = 0; i < size; i++) {
                distances[i] = Integer.MAX_VALUE;
            }
            distances[startVertex] = 0;
    
            for (int i = 0; i < size; i++) {
                int u = findMinDistanceVertex(distances, visited);
                if (u == -1) break;
    
                visited[u] = true;
    
                for (int v = 0; v < size; v++) {
                    if (!visited[v] && adjMatrix[u][v] != 0 && distances[u] != Integer.MAX_VALUE) 
{
                        int alt = distances[u] + adjMatrix[u][v];
                        if (alt < distances[v]) {
                            distances[v] = alt;
                        }
                    }
                }
            }
            return distances;
        }
    
        private int findVertexIndex(String data) {
            for (int i = 0; i < vertexData.length; i++) {
                if (vertexData[i].equals(data)) {
                    return i;
                }
            }
            return -1;
        }
    
        private int findMinDistanceVertex(int[] distances, boolean[] visited) {
            int minDistance = Integer.MAX_VALUE, minIndex = -1;
            for (int i = 0; i < size; i++) {
                if (!visited[i] && distances[i] < minDistance) {
                    minDistance = distances[i];
                    minIndex = i;
                }
            }
            return minIndex;
        }
    }
    
    public static void main(String[] args) {
        Graph g = new Graph(5);
    
        g.addVertexData(0, "Jalan Wani");
        g.addVertexData(1, "Jalan Melati");
        g.addVertexData(2, "Jalan Maluri");
        g.addVertexData(3, "Jalan Serdang");
        g.addVertexData(4, "Jalan Sri Gombak");
    
        g.addEdge(3, 4, 2); // D -> E, weight 2
        g.addEdge(3, 2, 4); // D -> C, weight 4
        g.addEdge(2, 0, 2); // C -> A, weight 2
        g.addEdge(2, 3, 3); // C -> D, weight 3
        g.addEdge(4, 0, 1); // E -> A, weight 1
        g.addEdge(4, 3, 4); // E -> D, weight 4
        g.addEdge(0, 3, 5); // A -> D, weight 5
        g.addEdge(0, 1, 4); // A -> B, weight 4
        g.addEdge(1, 2, 1); // B -> C, weight 1
        g.addEdge(1, 4, 6); // B -> E, weight 6
    
        // Dijkstra's algorithm from D to all vertices
        System.out.println("Dijkstra's Algorithm starting from vertex Jalan Serdang:\n");
        int[] distances = g.dijkstra("Jalan Serdang");
        for (int i = 0; i < distances.length; i++) {
            System.out.println("Shortest distance from Jalan Serdang (Evacuation Site) to " + 
g.vertexData[i] + ": " + distances[i]);
        }
    }
 }