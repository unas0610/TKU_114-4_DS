import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class Q07_RequestPipeline {

    public static boolean isBalanced(String text) {
        if (text == null) {
            return false;
        }
        if (text.isEmpty()) {
            return true;
        }
        Deque<Character> stack = new ArrayDeque<>();

        for (char ch : text.toCharArray()) {
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else if (ch == ')' || ch == ']' || ch == '}') {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if ((ch == ')' && top != '(') ||
                    (ch == ']' && top != '[') ||
                    (ch == '}' && top != '{')) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    private static String takeUrgentCheckpoint(Deque<String> urgentQueue) {
        return urgentQueue.poll();
    }

    public static List<String> process(String[] commands) {
        if (commands == null) {
            return Collections.emptyList();
        }
        Deque<String> normalQueue = new ArrayDeque<>();
        Deque<String> urgentQueue = new ArrayDeque<>();
        List<String> result = new ArrayList<>();

        for (String cmd : commands) {
            if (cmd == null || cmd.isBlank()) {
                continue;
            }
            String[] parts = cmd.trim().split("\\s+");
            if (parts.length == 0) {
                continue;
            }

            String action = parts[0];

            if ("PROCESS".equals(action)) {
                if (parts.length != 1) {
                    continue; 
                }

                if (!urgentQueue.isEmpty()) {
                    result.add(takeUrgentCheckpoint(urgentQueue));
                } else if (!normalQueue.isEmpty()) {
                    result.add(normalQueue.poll());
                } else {
                    result.add("EMPTY");
                }
            } else if ("NORMAL".equals(action)) {
                if (parts.length != 2) {
                    continue; 
                }
                normalQueue.offer(parts[1]);
            } else if ("URGENT".equals(action)) {
                if (parts.length != 2) {
                    continue; 
                }
                urgentQueue.offer(parts[1]);
            }
        }

        return result;
    }
}