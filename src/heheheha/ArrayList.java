package heheheha;

import java.util.Arrays;

public class ArrayList<T extends Comparable<T>> {

    private T[] data;
    private int size;

    @SuppressWarnings("unchecked")
    public MyArrayList(int capacity) {
        data = (T[]) new Comparable[capacity];
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (size >= data.length) {
            data = Arrays.copyOf(data, data.length * 2);
        }
    }

    // ----------------------
    // 1. APPEND
    // ----------------------
    public void append(T value) {
        ensureCapacity();
        data[size] = value;
        size++;
    }

    // ----------------------
    // 2. PREPEND
    // ----------------------
    public void prepend(T value) {
        ensureCapacity();
        // Shift everything right by 1
        for (int i = size; i > 0; i--) {
            data[i] = data[i - 1];
        }
        data[0] = value;
        size++;
    }

    // ----------------------
    // 3. INSERT AT INDEX
    // ----------------------
    public void insertAt(int index, T value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }

        ensureCapacity();

        // Shift right from index
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }

        data[index] = value;
        size++;
    }

    // ----------------------
    // 4. REMOVE AT INDEX
    // ----------------------
    public T removeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }

        T removed = data[index];

        // Shift left
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        data[size - 1] = null;
        size--;
        return removed;
    }

    // ----------------------
    // 5. SEARCH
    // Return index of element or -1 if not found
    // ----------------------
    public int search(T value) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    // ----------------------
    // 6. SORT
    // Bubble sort for simplicity
    // ----------------------
    public void sort(boolean ascending) {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {

                int comparison = data[j].compareTo(data[j + 1]);

                // If ascending AND data[j] > data[j+1], swap
                // If descending AND data[j] < data[j+1], swap
                if ((ascending && comparison > 0) ||
                    (!ascending && comparison < 0)) {

                    T temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }
    }

    // Optional helper to print
    public String toString() {
        return Arrays.toString(Arrays.copyOf(data, size));
    }
}
