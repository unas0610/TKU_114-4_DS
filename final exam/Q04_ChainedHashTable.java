import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Q04_ChainedHashTable {

    private static class Node {
        int key;
        String value;

        Node(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private final List<List<Node>> table;
    private final int bucketCount;
    private int size;

    public Q04_ChainedHashTable(int bucketCount) {
        if (bucketCount <= 0) {
            throw new IllegalArgumentException();
        }
        this.bucketCount = bucketCount;
        this.size = 0;
        this.table = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            this.table.add(new LinkedList<>());
        }
    }

    private int getBucketIndex(int key) {
        int index = key % bucketCount;
        if (index < 0) {
            index += bucketCount;
        }
        return index;
    }

    public void put(int key, String value) {
        int index = getBucketIndex(key);
        List<Node> bucket = table.get(index);

        for (Node node : bucket) {
            if (node.key == key) {
                node.value = value;
                return;
            }
        }

        bucket.add(new Node(key, value));
        size++;
    }

    public String get(int key) {
        int index = getBucketIndex(key);
        List<Node> bucket = table.get(index);

        for (Node node : bucket) {
            if (node.key == key) {
                return node.value;
            }
        }

        return null;
    }

    public boolean remove(int key) {
        int index = getBucketIndex(key);
        List<Node> bucket = table.get(index);

        for (int i = 0; i < bucket.size(); i++) {
            if (bucket.get(i).key == key) {
                bucket.remove(i);
                size--;
                return true;
            }
        }

        return false;
    }

    public int size() {
        return size;
    }

    public int longestChain() {
        int maxLen = 0;
        for (List<Node> bucket : table) {
            if (bucket.size() > maxLen) {
                maxLen = bucket.size();
            }
        }
        return maxLen;
    }
}