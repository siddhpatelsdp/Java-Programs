// Written by Siddh Patel
import java.util.*;

class MinHeap<T extends Comparable<T>> {
    private List<T> heap;

    // constructor.
    public MinHeap() {
        heap = new ArrayList<>();
    }

    // add an element to the heap.
    public void add(T value) {
        heap.add(value);
        heapifyUp(heap.size() - 1);
    }

    // remove and return the root (min value) from the heap.
    public T remove() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }

        T root = heap.get(0);
        T lastElement = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, lastElement);
            heapifyDown(0);
        }

        return root;
    }

    // helper function to restore heap property upwards.
    private void heapifyUp(int index) {
        int parentIndex = (index - 1) / 2;
        if (index > 0 && heap.get(index).compareTo(heap.get(parentIndex)) < 0) {
            Collections.swap(heap, index, parentIndex);
            heapifyUp(parentIndex);
        }
    }

    // helper function to restore heap property downwards.
    private void heapifyDown(int index) {
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;
        int smallest = index;

        if (leftChildIndex < heap.size() && heap.get(leftChildIndex).compareTo(heap.get(smallest)) < 0) {
            smallest = leftChildIndex;
        }

        if (rightChildIndex < heap.size() && heap.get(rightChildIndex).compareTo(heap.get(smallest)) < 0) {
            smallest = rightChildIndex;
        }

        if (smallest != index) {
            Collections.swap(heap, smallest, index);
            heapifyDown(smallest);
        }
    }

    // return the size of the heap.
    public int size() {
        return heap.size();
    }

    // check if the heap is empty.
    public boolean isEmpty() {
        return heap.isEmpty();
    }
}