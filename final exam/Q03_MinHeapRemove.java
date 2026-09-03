import java.util.ArrayList;
import java.util.List;

public class Q03_MinHeapRemove {

    private final List<Integer> heap = new ArrayList<>();

    public Q03_MinHeapRemove(List<Integer> values) {
        if (values != null) {
            for (Integer val : values) {
                if (val != null) {
                    heap.add(val);
                }
            }
        }
        heapify();
    }

    public Integer removeMin() {
        if (heap.isEmpty()) {
            return null;
        }

        int minVal = heap.get(0);
        int lastVal = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, lastVal);
            bubbleDown(0);
        }

        return minVal;
    }

    public Integer peek() {
        if (heap.isEmpty()) {
            return null;
        }
        return heap.get(0);
    }

    public int size() {
        return heap.size();
    }

    public List<Integer> snapshot() {
        return new ArrayList<>(heap);
    }

    private void heapify() {
        int n = heap.size();
        for (int i = (n - 2) / 2; i >= 0; i--) {
            bubbleDown(i);
        }
    }

    private void bubbleDown(int index) {
        int n = heap.size();
        while (true) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int smallest = index;

            if (left < n && heap.get(left) < heap.get(smallest)) {
                smallest = left;
            }

            if (right < n && heap.get(right) < heap.get(smallest)) {
                smallest = right;
            }

            if (smallest != index) {
                swap(index, smallest);
                index = smallest;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}