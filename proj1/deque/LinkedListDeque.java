package deque;

public class LinkedListDeque<T> {
    private class Node {
        private Node prev, next;
        private T item;

        private Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private int size;
    private Node sentinel;

    public LinkedListDeque(T item) {
        size = 1;
        sentinel = new Node(null, null, null);
        sentinel.next = new Node(sentinel, item, sentinel);
        sentinel.prev = sentinel.next;
    }

    public LinkedListDeque() {
        size = 0;
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public void addFirst(T item) {
        Node newNode = new Node(sentinel, item, sentinel.next);
        newNode.next.prev = newNode;
        sentinel.next = newNode;
        size += 1;
    }

    public void addLast(T item) {
        Node newNode = new Node(sentinel.prev, item, sentinel);
        newNode.prev.next = newNode;
        sentinel.prev = newNode;
        size += 1;
    }

    public T removeFirst() {
        Node removedNode = sentinel.next;
        if (removedNode == sentinel) {
            return null;
        }
        removedNode.next.prev = sentinel;
        sentinel.next = removedNode.next;
        removedNode.prev = null;
        removedNode.next = null;
        size -= 1;
        return removedNode.item;
    }

    public T removeLast() {
        Node removedNode = sentinel.prev;
        if (removedNode == sentinel) {
            return null;
        }
        removedNode.prev.next = sentinel;
        sentinel.prev = removedNode.prev;
        removedNode.prev = null;
        removedNode.next = null;
        size -= 1;
        return removedNode.item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (Node i = sentinel.next; i != sentinel; i = i.next) {
            System.out.print(i.item);
            if (i.next != sentinel) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node iterator = sentinel.next;
        for (int i = 0; i < index; ++i) {
            iterator = iterator.next;
        }
        return iterator.item;
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node p = sentinel.next;
        return recursionHelper(index, p);
    }

    private T recursionHelper(int index, Node p) {
        if (index == 0) {
            return p.item;
        }
        return recursionHelper(index - 1, p.next);
    }
}
