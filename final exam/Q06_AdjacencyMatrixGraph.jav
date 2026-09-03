import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q06_AdjacencyMatrixGraph {

    private final List<String> vertexList;
    private final Map<String, Integer> vertexIndexMap;
    private final boolean[][] matrix;

    public Q06_AdjacencyMatrixGraph(List<String> vertices) {
        this.vertexList = new ArrayList<>();
        this.vertexIndexMap = new HashMap<>();

        if (vertices != null) {
            for (String v : vertices) {
                if (v != null && !vertexIndexMap.containsKey(v)) {
                    vertexIndexMap.put(v, vertexList.size());
                    vertexList.add(v);
                }
            }
        }

        int size = vertexList.size();
        this.matrix = new boolean[size][size];
    }

    public boolean addEdge(String first, String second) {
        if (first == null || second == null || first.equals(second)) {
            return false;
        }

        Integer u = vertexIndexMap.get(first);
        Integer v = vertexIndexMap.get(second);

        if (u == null || v == null) {
            return false;
        }

        if (matrix[u][v]) {
            return false;
        }

        matrix[u][v] = true;
        matrix[v][u] = true;
        return true;
    }

    public boolean removeEdge(String first, String second) {
        if (first == null || second == null) {
            return false;
        }

        Integer u = vertexIndexMap.get(first);
        Integer v = vertexIndexMap.get(second);

        if (u == null || v == null) {
            return false;
        }

        if (!matrix[u][v]) {
            return false;
        }

        matrix[u][v] = false;
        matrix[v][u] = false;
        return true;
    }

    public boolean hasEdge(String first, String second) {
        if (first == null || second == null) {
            return false;
        }

        Integer u = vertexIndexMap.get(first);
        Integer v = vertexIndexMap.get(second);

        if (u == null || v == null) {
            return false;
        }

        return matrix[u][v];
    }

    public int degree(String vertex) {
        if (vertex == null) {
            return -1;
        }

        Integer u = vertexIndexMap.get(vertex);
        if (u == null) {
            return -1;
        }

        int deg = 0;
        for (int j = 0; j < matrix[u].length; j++) {
            if (matrix[u][j]) {
                deg++;
            }
        }
        return deg;
    }

    public List<String> neighbors(String vertex) {
        if (vertex == null) {
            return Collections.emptyList();
        }

        Integer u = vertexIndexMap.get(vertex);
        if (u == null) {
            return Collections.emptyList();
        }

        List<String> neighborList = new ArrayList<>();
        for (int j = 0; j < matrix[u].length; j++) {
            if (matrix[u][j]) {
                neighborList.add(vertexList.get(j));
            }
        }
        return neighborList;
    }
}