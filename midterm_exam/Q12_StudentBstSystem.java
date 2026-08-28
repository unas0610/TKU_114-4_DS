import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Q12_StudentBstSystem {

    public static class Student {
        private final int id;
        private final String name;
        private int score;

        public Student(int id, String name, int score) {
            if (id <= 0) {
                throw new IllegalArgumentException("Student ID must be greater than 0.");
            }
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("Student name cannot be null or blank.");
            }

            this.id = id;
            this.name = name;
            this.score = Math.min(100, Math.max(0, score));
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = Math.min(100, Math.max(0, score));
        }

        @Override
        public String toString() {
            return id + "|" + name + "|" + score;
        }
    }

    private static class Node {
        Student student;
        Node left;
        Node right;

        Node(Student student) {
            this.student = student;
        }
    }

    private Node root;

    public Q12_StudentBstSystem() {
        this.root = null;
    }

    public boolean add(Student student) {
        if (student == null) {
            return false;
        }

        if (root == null) {
            root = new Node(student);
            return true;
        }

        Node current = root;
        while (true) {
            if (student.getId() == current.student.getId()) {
                return false; 
            } else if (student.getId() < current.student.getId()) {
                if (current.left == null) {
                    current.left = new Node(student);
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new Node(student);
                    return true;
                }
                current = current.right;
            }
        }
    }

    public Student find(int id) {
        Node current = root;
        while (current != null) {
            if (id == current.student.getId()) {
                return current.student;
            } else if (id < current.student.getId()) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return null;
    }

    public boolean updateScore(int id, int score) {
        Student target = find(id);
        if (target == null) {
            return false;
        }
        target.setScore(score);
        return true;
    }

    public boolean remove(int id) {
        if (find(id) == null) {
            return false;
        }
        root = removeNode(root, id);
        return true;
    }

    private Node removeNode(Node node, int id) {
        if (node == null) {
            return null;
        }

        if (id < node.student.getId()) {
            node.left = removeNode(node.left, id);
        } else if (id > node.student.getId()) {
            node.right = removeNode(node.right, id);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            Node successor = findMin(node.right);
            node.student = successor.student;
            node.right = removeNode(node.right, successor.student.getId());
        }

        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public List<Student> studentsBetween(int lowId, int highId) {
        if (lowId > highId || root == null) {
            return Collections.emptyList();
        }

        List<Student> result = new ArrayList<>();
        rangeSearchHelper(root, lowId, highId, result);
        return result;
    }

    private void rangeSearchHelper(Node node, int low, int high, List<Student> result) {
        if (node == null) {
            return;
        }

        if (node.student.getId() > low) {
            rangeSearchHelper(node.left, low, high, result);
        }

  
        if (node.student.getId() >= low && node.student.getId() <= high) {
            result.add(node.student);
        }

        if (node.student.getId() < high) {
            rangeSearchHelper(node.right, low, high, result);
        }
    }

    public List<Student> inorder() {
        if (root == null) {
            return Collections.emptyList();
        }

        List<Student> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }

    private void inorderHelper(Node node, List<Student> result) {
        if (node == null) {
            return;
        }
        inorderHelper(node.left, result);
        result.add(node.student);
        inorderHelper(node.right, result);
    }
}