import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Q06_EnrollmentIndex {

    private final Map<String, Set<String>> enrollmentMapR26;

    public Q06_EnrollmentIndex() {
        this.enrollmentMapR26 = new HashMap<>();
    }

    public boolean enroll(String courseCode, String studentId) {
        if (isInvalid(courseCode) || isInvalid(studentId)) {
            return false;
        }
        Set<String> students = enrollmentMapR26.computeIfAbsent(courseCode, k -> new HashSet<>());
        return students.add(studentId);
    }

    public boolean drop(String courseCode, String studentId) {
        if (isInvalid(courseCode) || isInvalid(studentId)) {
            return false;
        }

        Set<String> students = enrollmentMapR26.get(courseCode);
        if (students == null) {
            return false;
        }

        boolean removed = students.remove(studentId);

        if (removed && students.isEmpty()) {
            enrollmentMapR26.remove(courseCode);
        }

        return removed;
    }

    public int courseSize(String courseCode) {
        if (isInvalid(courseCode)) {
            return 0;
        }
        Set<String> students = enrollmentMapR26.get(courseCode);
        return students == null ? 0 : students.size();
    }

    public List<String> studentsOf(String courseCode) {
        if (isInvalid(courseCode) || !enrollmentMapR26.containsKey(courseCode)) {
            return Collections.emptyList();
        }

        List<String> studentList = new ArrayList<>(enrollmentMapR26.get(courseCode));
        Collections.sort(studentList);
        return studentList;
    }

    public List<String> coursesOf(String studentId) {
        if (isInvalid(studentId)) {
            return Collections.emptyList();
        }

        List<String> courseList = new ArrayList<>();
        for (Map.Entry<String, Set<String>> entry : enrollmentMapR26.entrySet()) {
            if (entry.getValue().contains(studentId)) {
                courseList.add(entry.getKey());
            }
        }
        Collections.sort(courseList);
        return courseList;
    }

    public Map<String, Integer> summary() {
        Map<String, Integer> summaryMap = new TreeMap<>();
        for (Map.Entry<String, Set<String>> entry : enrollmentMapR26.entrySet()) {
            summaryMap.put(entry.getKey(), entry.getValue().size());
        }
        return summaryMap;
    }

    private boolean isInvalid(String str) {
        return str == null || str.isBlank();
    }
}