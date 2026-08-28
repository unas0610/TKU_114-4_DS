import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Q10_BstDirectory {

    private static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    private Node root;
    private int count;

    public Q10_BstDirectory() {
        this.root = null;
        this.count = 0;
    }

    public boolean add(int value) {
        if (root == null) {
            root = new Node(value);
            count++;
            return true;
        }

        Node current = root;
        while (true) {
            if (value == current.value) {
                return false; 
            } else if (value < current.value) {
                if (current.left == null) {
                    current.left = new Node(value);
                    count++;
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new Node(value);
                    count++;
                    return true;
                }
                current = current.right;
            }
        }
    }

    public boolean contains(int value) {
        Node current = root;
        while (current != null) {
            if (value == current.value) {
                return true;
            } else if (value < current.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }

    public int size() {
        return count;
    }

    public List<Integer> searchPath(int target) {
        // bst-path-check T10-73
        if (root == null) {
            return Collections.emptyList();
        }

        List<Integer> path = new ArrayList<>();
        Node current = root;

        while (current != null) {
            path.add(current.value); 
            if (target == current.value) {
                break;
            } else if (target < current.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return path;
    }

    public List<Integer> inorder() {
        if (root == null) {
            return Collections.emptyList();
        }
        List<Integer> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }

    private void inorderHelper(Node node, List<Integer> result) {
        if (node == null) {
            return;
        }
        inorderHelper(node.left, result);
        result.add(node.value);
        inorderHelper(node.right, result);
    }

    public boolean isValid() {
        return validateBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validateBST(Node node, long minBoundary, long maxBoundary) {
        if (node == null) {
            return true;
        }

        if (node.value <= minBoundary || node.value >= maxBoundary) {
            return false;
        }
        return validateBST(node.left, minBoundary, node.value) &&
               validateBST(node.right, node.value, maxBoundary);
    }
}