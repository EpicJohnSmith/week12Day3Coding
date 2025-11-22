package heheheha;

import java.util.Arrays;

public class ArrayList<T extends Comparable<T>>
{

    private T[] data;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayList(int capacity)
    {
        data = (T[]) new Comparable[capacity];
        size = 0;
    }

    public int size()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    private void ensureCapacity()
    {
        if (size >= data.length)
        {
            data = Arrays.copyOf(data, data.length * 2);
        }
    }

    public void append(T value)
    {
        ensureCapacity();
        data[size] = value;
        size++;
    }

    public void prepend(T value)
    {
        ensureCapacity(); // Needed some help here, not exactly sure what this was about. AI documentation
        for (int i = size; i > 0; i--)
        {
            data[i] = data[i - 1];
        }
        data[0] = value;
        size++;
    }

    public void insertAt(int index, T value)
    {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index); // IndexOutOfBounds...I think this is due to whether the array will have a issue 
        }

        ensureCapacity();

        for (int i = size; i > index; i--)
        {
            data[i] = data[i - 1];
        }

        data[index] = value;
        size++;
    }

    public T removeAt(int index)
    {
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException("Index: " + index);
        }

        T removed = data[index];

        for (int i = index; i < size - 1; i++)
        {
            data[i] = data[i + 1];
        }

        data[size - 1] = null;
        size--;
        return removed;
    }

    public int search(T value)
    {
        for (int i = 0; i < size; i++)
        {
            if (data[i].equals(value))
            {
                return i;
            }
        }
        return -1;
    }

    public void sort(boolean ascending)
    {
        for (int i = 0; i < size - 1; i++)
        {
            for (int j = 0; j < size - i - 1; j++)
            {

                int comparison = data[j].compareTo(data[j + 1]); // Needed some help with AI here

                if ((ascending && comparison > 0) ||
                    (!ascending && comparison < 0))
                {

                    T temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }
    }

    public String toString()
    {
        return Arrays.toString(Arrays.copyOf(data, size)); // Needed to look up the right documentation for this
    }

    public static void main(String[] args)
    {

        ArrayList<Integer> list = new ArrayList<>(5); // Not really sure what this meant, but it makes it work!

        list.append(10);
        list.append(3);
        list.append(8);

        list.prepend(20);        // [20, 10, 3, 8]
        list.insertAt(2, 99);    // [20, 10, 99, 3, 8]

        System.out.println("List: " + list);

        list.removeAt(1);        // removes 10
        System.out.println("After remove: " + list);

        System.out.println("Searching 99: index = " + list.search(99));

        list.sort(true);         // ascending
        System.out.println("Sorted ascending: " + list);

        list.sort(false);        // descending
        System.out.println("Sorted descending: " + list);
    }
}