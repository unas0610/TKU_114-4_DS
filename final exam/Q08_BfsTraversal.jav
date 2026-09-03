import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Q08_BfsTraversal {

    public static List<String> bfs(Map<String, List<String>> graph, String start) {
        if (graph == null || start == null || !graph.containsKey(start)) {
            return new ArrayList<>();
        }

        List<String> order = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            order.add(current);

            List<String> neighbors = graph.get(current);
            if (neighbors != null) {
                for (String neighbor : neighbors) {
                    if (neighbor != null && !visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.offer(neighbor);
                    }
                }
            }
        }

        return order;
    }

    public static Map<String, Integer> distanceFrom(Map<String, List<String>> graph, String start) {
        if (graph == null || start == null || !graph.containsKey(start)) {
            return new HashMap<>();
        }

        Map<String, Integer> distances = new HashMap<>();
        Queue<String> queue = new LinkedList<>();

        distances.put(start, 0);
        queue.offer(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            int currentDist = distances.get(current);

            List<String> neighbors = graph.get(current);
            if (neighbors != null) {
                for (String neighbor : neighbors) {
                    if (neighbor != null && !distances.containsKey(neighbor)) {
                        distances.put(neighbor, currentDist + 1);
                        queue.offer(neighbor);
                    }
                }
            }
        }

        return distances;
    }
}