import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Q01_PriorityRecord {

    public record Job(String id, int priority, long sequence) {}

    public static List<String> processOrder(List<Job> jobs) {
        if (jobs == null || jobs.isEmpty()) {
            return new ArrayList<>();
        }

        return jobs.stream()
                .filter(Objects::nonNull)
                        .thenComparingLong(Job::sequence)
                        .thenComparing(Job::id, Comparator.nullsLast(Comparator.naturalOrder())))

                .map(Job::id)
                .toList();
    }
}