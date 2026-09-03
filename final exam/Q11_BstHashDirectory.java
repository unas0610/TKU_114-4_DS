import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q11_BstHashDirectory {

    private static class TreeNode {
        int id;
        TreeNode left;
        TreeNode right;

        TreeNode(int id) {
            this.id = id;
        }
    }

    private TreeNode root;
    private final Map<Integer, String> nameMap = new HashMap<>();

    public boolean add(int id, String name) {
        if (id <= 0 || name == null) {
            return false;
        }
        String trimmedName = name.trim();
        if (trimmedName.isEmpty()) {
            return false;
        }

        if (nameMap.containsKey(id)) {
            return false;
        }

        root = insertNode(root, id);
        nameMap.put(id, trimmedName);
        return true;
    }

    public String findName(int id) {
        return nameMap.get(id);
    }

    public boolean remove(int id) {
        if (!nameMap.containsKey(id)) {
            return false;
        }

        root = deleteNode(root, id);
        nameMap.remove(id);
        return true;
    }

    public List<Integer> idsBetween(int low, int high) {
        List<Integer> result = new ArrayList<>();
        if (low > high) {
            return result;
        }
        rangeSearch(root, low, high, result);
        return result;
    }

    public int size() {
        return nameMap.size();
    }

    private TreeNode insertNode(TreeNode node, int id) {
        if (node == null) {
            return new TreeNode(id);
        }
        if (id < node.id) {
            node.left = insertNode(node.left, id);
        } else if (id > node.id) {
            node.right = insertNode(node.right, id);
        }
        return node;
    }

    private TreeNode deleteNode(TreeNode node, int id) {
        if (node == null) {
            return null;
        }

        if (id < node.id) {
            node.left = deleteNode(node.left, id);
        } else if (id > node.id) {
            node.right = deleteNode(node.right, id);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            TreeNode minNode = findMin(node.right);
            node.id = minNode.id;
            node.right = deleteNode(node.right, minNode.id);
        }
        return node;
    }

    private TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private void rangeSearch(TreeNode node, int low, int high, List<Integer> result) {
        if (node == null) {
            return;
        }

        if (node.id > low) {
            rangeSearch(node.left, low, high, result);
        }

        if (node.id >= low && node.id <= high) {
            result.add(node.id);
        }

        if (node.id < high) {
            rangeSearch(node.right, low, high, result);
        }
    }
}