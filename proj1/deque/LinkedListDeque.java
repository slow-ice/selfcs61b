package deque;

import java.util.Iterator;

public class LinkedListDeque<Elem> implements Deque<Elem>, Iterable<Elem> {
    private Node sentinel = new Node(null, null, null);
    private int length;

    public LinkedListDeque() {
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        length = 0;
    }

    public LinkedListDeque(Elem item) {
        Node newnode = new Node(item, sentinel, sentinel);
        sentinel.next = newnode;
        sentinel.prev = newnode;
        length = 1;
    }


    @Override
    public void addFirst(Elem item) {
        Node firstnode = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = firstnode;
        sentinel.next = firstnode;
        length++;
    }

    @Override
    public void addLast(Elem item) {
        Node lastnode = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = lastnode;
        sentinel.prev = lastnode;
        length++;
    }

    @Override
    public int size() {
        return length;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public void printDeque() {
        Node curr = sentinel.next;
        while(curr.next != sentinel) {
            System.out.print(curr.toString() + " ");
            curr = curr.next;
        }
    }

    @Override
    public Elem removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Elem firstnode = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        length--;
        return firstnode;
    }

    @Override
    public Elem removeLast() {
        if (isEmpty()) {
            return null;
        }
        Elem lastnode = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        return lastnode;
    }

    @Override
    public Elem get(int index) {
        if (length < index)
            return null;
        int pos = 1;
        Node curr = sentinel.next;
        while(pos < index) {
            pos ++;
            curr = curr.next;
        }
        return curr.item;
    }

    @Override
    public Iterator<Elem> iterator() {
        return new LlistIterator();
    }

    private class LlistIterator implements Iterator<Elem>{
        private Node p;

        LlistIterator() {
            p = sentinel.next;
        }

        @Override
        public boolean hasNext() {
            return p != sentinel;
        }

        @Override
        public Elem next() {
            Elem tmp = p.item;
            p = p.next;
            return tmp;
        }
    }

    private Elem getRecursiveHelper(Node now, int index) {
        if (index == 0) {
            return now.item;
        }
        return getRecursiveHelper(now.next, index - 1);
    }

    public Elem getRecursive(int index) {
        if (length < index) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    private class Node {
        private Elem item;
        private Node prev;
        private Node next;

        public Node(Elem val, Node pre, Node nxt) {
            item = val;
            this.next = nxt;
            this.prev = pre;
        }

        @Override
        public String toString() {
            if(item == null) {
                return "null";
            }
            return item.toString();
        }
    }

}
