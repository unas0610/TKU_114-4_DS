import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Q11_BstDeletion {

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

    public Q11_BstDeletion() {
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
                return false; // 重複值不加入
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

    public boolean remove(int value) {
        if (!contains(value)) {
            return false;
        }
        root = removeNode(root, value);
        count--;
        return true;
    }

    private Node removeNode(Node node, int value) {
        if (node == null) {
            return null;
        }

        if (value < node.value) {
            node.left = removeNode(node.left, value);
        } else if (value > node.value) {
            node.right = removeNode(node.right, value);
        } else {

            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            Node successorAuditN11 = findMin(node.right);
            node.value = successorAuditN11.value;
            node.right = removeNode(node.right, successorAuditN11.value);
        }

        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
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