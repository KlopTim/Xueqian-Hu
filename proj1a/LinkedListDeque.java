public class LinkedListDeque<T> {
    private class TNode {
        T item;
        TNode prev;
        TNode next;
        public TNode() {
            this.prev = null;
            this.next = null;
        }
        public TNode(T item, TNode prev, TNode next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private int size;
    private TNode sentinal;

    public LinkedListDeque() {
        sentinal = new TNode();
        sentinal.next = sentinal;
        sentinal.prev = sentinal;
        size = 0;
    }

    public void addFirst(T item) {
        sentinal.next = new TNode(item, sentinal, sentinal.next);
        sentinal.next.next.prev = sentinal.next;
        size += 1;
    }

    public void addLast(T item) {
        sentinal.prev = new TNode(item, sentinal.prev, sentinal);
        sentinal.prev.prev.next = sentinal.prev;
        size += 1;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        TNode p = sentinal;
        for (int i = 0; i < size; i += 1) {
            p = p.next;
            System.out.print(p.item);
            System.out.print(" ");
        }
        System.out.println("");
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T first = sentinal.next.item;
        sentinal.next = sentinal.next.next;
        sentinal.next.prev = sentinal;
        size -= 1;
        return first;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T last = sentinal.prev.item;
        sentinal.prev = sentinal.prev.prev;
        sentinal.prev.next = sentinal;
        size -= 1;
        return last;
    }

    public T get(int index) {
        TNode p = sentinal;
        for (int i = 0; i <= index; i += 1) {
            p = p.next;
        }
        return p.item;
    }

    private T subGet(TNode p, int index) {
        if (index == 0) {
            return p.item;
        }
        return subGet(p.next, index - 1);
    }

    public T getRecursive(int index) {
        return subGet(sentinal.next, index);
    }
/*
    public static void main(String[] args) {
        LinkedListDeque<Integer> L = new LinkedListDeque<Integer>();
        L.addFirst(1);
        L.addFirst(2);
        L.addLast(0);
        System.out.println(L.getRecursive(2));
    }
 */
}
