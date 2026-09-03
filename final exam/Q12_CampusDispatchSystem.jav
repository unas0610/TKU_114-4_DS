import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Q12_CampusDispatchSystem {

    public record Request(String id, String location, int priority, long sequence) {}

    private final Map<String, Set<String>> graph = new HashMap<>();
    private final Set<String> requestIds = new HashSet<>();
    private final PriorityQueue<Request> pq = new PriorityQueue<>((a, b) -> {
        if (a.priority() != b.priority()) {
            return Integer.compare(a.priority(), b.priority());
        }
        return Long.compare(a.sequence(), b.sequence());
    });

    public boolean addLocation(String location) {
        if (location == null || graph.containsKey(location)) {
            return false;
        }
        graph.put(location, new HashSet<>());
        return true;
    }

    public boolean addRoad(String first, String second) {
        if (first == null || second == null || first.equals(second)) {
            return false;
        }
        if (!graph.containsKey(first) || !graph.containsKey(second)) {
            return false;
        }

        graph.get(first).add(second);
        graph.get(second).add(first);
        return true;
    }

    public boolean submit(Request request) {
        if (request == null || request.id() == null || request.location() == null) {
            return false;
        }
        if (!graph.containsKey(request.location())) {
            return false;
        }
        if (requestIds.contains(request.id())) {
            return false;
        }

        requestIds.add(request.id());
        pq.offer(request);
        return true;
    }

    public Request nextReachable(String serviceCenter) {
        if (serviceCenter == null || !graph.containsKey(serviceCenter) || pq.isEmpty()) {
            return null;
        }

        List<Request> stashed = new ArrayList<>();
        Request targetRequest = null;

        while (!pq.isEmpty()) {
            Request current = pq.poll();
            if (isReachable(serviceCenter, current.location())) {
                targetRequest = current;
                break;
            } else {
                stashed.add(current);
            }
        }

        pq.addAll(stashed);

        if (targetRequest != null) {
            requestIds.remove(targetRequest.id());
        }

        return targetRequest;
    }

    public List<String> route(String start, String target) {
        if (start == null || target == null || !graph.containsKey(start) || !graph.containsKey(target)) {
            return Collections.emptyList();
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

            for (String neighbor : graph.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);
                    queue.offer(neighbor);
                }
            }
        }

        if (!found) {
            return Collections.emptyList();
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

    public int pendingCount() {
        return pq.size();
    }

    private boolean isReachable(String start, String target) {
        if (start.equals(target)) {
            return true;
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();

            if (current.equals(target)) {
                return true;
            }

            for (String neighbor : graph.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }

        return false;
    }
}