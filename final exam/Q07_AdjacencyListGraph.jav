import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Q07_AdjacencyListGraph {

    private final Map<String, Set<String>> adjMap = new HashMap<>();
    private final Map<String, Integer> inDegreeMap = new HashMap<>();
    private int totalEdges = 0;

    public boolean addVertex(String vertex) {
        if (vertex == null || adjMap.containsKey(vertex)) {
            return false;
        }
        adjMap.put(vertex, new LinkedHashSet<>());
        inDegreeMap.put(vertex, 0);
        return true;
    }

    public boolean addEdge(String from, String to) {
        if (from == null || to == null || from.equals(to)) {
            return false;
        }

        Set<String> neighbors = adjMap.get(from);
        if (neighbors == null || !adjMap.containsKey(to)) {
            return false;
        }

        if (neighbors.contains(to)) {
            return false;
        }

        neighbors.add(to);
        inDegreeMap.put(to, inDegreeMap.get(to) + 1);
        totalEdges++;
        return true;
    }

    public boolean removeEdge(String from, String to) {
        if (from == null || to == null) {
            return false;
        }

        Set<String> neighbors = adjMap.get(from);
        if (neighbors == null || !adjMap.containsKey(to)) {
            return false;
        }

        if (!neighbors.contains(to)) {
            return false;
        }

        neighbors.remove(to);
        inDegreeMap.put(to, inDegreeMap.get(to) - 1);
        totalEdges--;
        return true;
    }

    public List<String> outgoing(String vertex) {
        if (vertex == null) {
            return Collections.emptyList();
        }

        Set<String> neighbors = adjMap.get(vertex);
        if (neighbors == null) {
            return Collections.emptyList();
        }

        return new ArrayList<>(neighbors);
    }

    public int inDegree(String vertex) {
        if (vertex == null) {
            return 0;
        }

        Integer deg = inDegreeMap.get(vertex);
        return deg != null ? deg : 0;
    }

    public int edgeCount() {
        return totalEdges;
    }
}