package deque;

public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 7;
        nextLast = 0;
    }

    private int mod(int index) {
        int m = items.length;
        return (index % m + m) % m;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        for (int i = 0; i < size; ++i) {
            a[i] = get(i);
        }
        items = a;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextFirst] = item;
        nextFirst = mod(nextFirst - 1);
        size += 1;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextLast] = item;
        nextLast = mod(nextLast + 1);
        size += 1;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if (size < items.length / 4 && items.length >= 16) {
            resize(items.length / 2);
        }
        nextFirst = mod(nextFirst + 1);
        T removedItem = items[nextFirst];
        items[nextFirst] = null;
        size -= 1;
        return removedItem;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if (size < items.length / 4 && items.length >= 16) {
            resize(items.length / 2);
        }
        nextLast = mod(nextLast - 1);
        T removedItem = items[nextLast];
        items[nextLast] = null;
        size -= 1;
        return removedItem;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; ++i) {
            System.out.print(get(i));
            if (i != size - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return items[mod(nextFirst + 1 + index)];
    }

    public T getLast() {
        return get(size - 1);
    }
}
