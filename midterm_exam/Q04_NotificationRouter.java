import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Q04_NotificationRouter {

    public interface Channel {
        String name();
        boolean supports(String destination);
        String send(String destination, String message);
    }

    public static class EmailChannel implements Channel {
        @Override
        public String name() {
            return "EMAIL";
        }

        @Override
        public boolean supports(String destination) {
            if (destination == null) {
                return false;
            }
            int atIndex = destination.indexOf('@');
            if (atIndex <= 0 || atIndex == destination.length() - 1) {
                return false;
            }
            return destination.indexOf('@', atIndex + 1) == -1;
        }

        @Override
        public String send(String destination, String message) {
            return name() + "|" + destination + "|" + message;
        }
    }

    public static class SmsChannel implements Channel {
        @Override
        public String name() {
            return "SMS";
        }

        @Override
        public boolean supports(String destination) {
            if (destination == null) {
                return false;
            }
            String sanitized = destination.replace("-", "");
            if (sanitized.length() != 10) {
                return false;
            }
            for (char c : sanitized.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public String send(String destination, String message) {
            return name() + "|" + destination + "|" + message;
        }
    }

    private static void routeCheckpointM26() {
        // Private helper checkpoint
    }

    public static List<String> route(List<Channel> channels, String destination, String message) {
        routeCheckpointM26();
        if (channels == null || destination == null || message == null) {
            return Collections.emptyList();
        }

        List<String> results = new ArrayList<>();

        for (Channel channel : channels) {
            if (channel == null) {
                continue;
            }
            if (channel.supports(destination)) {
                results.add(channel.send(destination, message));
            }
        }

        return results;
    }
}