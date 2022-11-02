package deque;

import java.util.Iterator;

public class ArrayDeque<Elem> implements Deque<Elem>, Iterable<Elem> {
    private Elem[] node;
    private int length;
    private int front;
    private int back;

    public ArrayDeque() {
        node = (Elem[]) new Object[8];
        front = 3;
        back = 4;
        length = 0;
    }

    public ArrayDeque(Elem item) {
        node = (Elem[]) new Object[8];
        node[3] = item;
        front = 2;
        back = 4;
        length = 1;
    }

    @Override
    public void addFirst(Elem item) {
        node[front] = item;
        length ++;
        front --;
        if (front == -1)
            resize(length * 2);
    }

    @Override
    public void addLast(Elem item) {
        node[back] = item;
        back++;
        length++;
        if (back == node.length)
            resize(length * 2);
    }

    private void resize(int newsize) {
        Elem[] newnodes = (Elem[]) new Object[newsize];
        int newfront = Math.abs(newsize - length) / 2;
        System.arraycopy(node, front + 1, newnodes, newfront, length);
        node = newnodes;
        front = newfront - 1;
        back = newfront + length;
    }

    private void shrinkSize() {
        if (isEmpty())
            resize(8);
        else if (node.length / 4 > length && length >= 4)
            resize(length * 2);
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public void printDeque() {

    }

    @Override
    public Elem removeFirst() {
        if (isEmpty())
            return null;
        front++;
        Elem firstnode = node[front];
        node[front] = null;
        length--;
        shrinkSize();
        return firstnode;
    }

    @Override
    public Elem removeLast() {
        if (isEmpty())
            return null;
        back--;
        Elem lastnode = node[back];
        node[back] = null;
        length--;
        shrinkSize();
        return lastnode;
    }

    @Override
    public Elem get(int index) {
        if (index < 0 || index >= length)
            return null;
        return node[front + 1 + index];
    }

    @Override
    public Iterator<Elem> iterator() {
        return new ArrayDequeIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o == this)
            return true;
        if (!(o instanceof ArrayDeque))
            return false;
        ArrayDeque<?> tmp = (ArrayDeque<?>) o;
        if (tmp.size() != length)
            return false;
        for (int i = 0; i < length; i++) {
            if (tmp.get(i) != this.get(i))
                return false;
        }
        return true;
    }

    private class ArrayDequeIterator implements Iterator<Elem>{
        private int index;
        ArrayDequeIterator() {
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < length;
        }

        @Override
        public Elem next() {
            Elem nextnode = get(index);
            index++;
            return nextnode;
        }
    }
}
