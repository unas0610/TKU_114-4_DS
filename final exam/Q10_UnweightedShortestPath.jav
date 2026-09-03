import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Q10_UnweightedShortestPath {

    public static List<String> shortestPath(Map<String, List<String>> graph, String start, String target) {
        if (graph == null || start == null || target == null) {
            return new ArrayList<>();
        }

        if (!graph.containsKey(start) || !graph.containsKey(target)) {
            return new ArrayList<>();
        }

        if (start.equals(target)) {
            List<String> path = new ArrayList<>();
            path.add(start);
            return path;
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Map<String, String> parentMap = new HashMap<>();

        queue.offer(start);
        visited.add(start);

        boolean found = false;

        while (!queue.isEmpty()) {
            String current = queue.poll();

            if (current.equals(target)) {
                found = true;
                break;
            }

            List<String> neighbors = graph.get(current);
            if (neighbors != null) {
                for (String neighbor : neighbors) {
                    if (neighbor != null && !visited.contains(neighbor)) {
                        visited.add(neighbor);
                        parentMap.put(neighbor, current);
                        queue.offer(neighbor);
                    }
                }
            }
        }

        if (!found) {
            return new ArrayList<>();
        }

        List<String> path = new ArrayList<>();
        String current = target;
        while (current != null) {
            path.add(current);
            current = parentMap.get(current);
        }

        Collections.reverse(path);
        return path;
    }
}