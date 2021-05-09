public class ArrayDeque<T> {
    private T[] items;
    private int begin;
    private int end;
    private int size;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        begin = 0;
        end = 0;
        size = 0;
    }

    private void addResize() {
        T[] newItems = (T[]) new Object[items.length * 2];
        int i = begin;
        for (int count = 0; count < size; count += 1) {
            newItems[count] = items[i];
            i = (i + 1) % items.length;
        }
        items = newItems;
        begin = 0;
        end = size;
    }

    public void addFirst(T item) {
        if (size >= items.length) {
            addResize();
        }
        begin = (begin - 1 + items.length) % items.length;
        items[begin] = item;
        size += 1;
    }

    public void addLast(T item) {
        if (size >= items.length) {
            addResize();
        }
        items[end] = item;
        end = (end + 1) % items.length;
        size += 1;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int i = begin;
        for (int count = 0; count < size; count += 1) {
            System.out.print(items[i]);
            System.out.print(" ");
            i = (i + 1) % items.length;
        }
        System.out.println("");
    }

    public void delResize() {
        int reLen = items.length / 2;
        T[] newItems = (T[]) new Object[reLen];
        int i = begin;
        for (int count = 0; count < size; count += 1) {
            newItems[count] = items[i];
            i = (i + 1) % items.length;
        }
        items = newItems;
        begin = 0;
        end = size;
    }

    public T removeFirst() {
        if (items.length >= 16 && 4 * (size -1) / items.length < 1) {
            delResize();

        }
        T removeNum = items[begin];
        begin = (begin + 1) % items.length;
        size -= 1;
        return removeNum;
    }

    public T removeLast() {
        if (items.length >= 16 && 4 * (size - 1) / items.length < 1) {
            delResize();
        }
        end = (end - 1 + items.length) % items.length;
        size -= 1;
        return items[end];
    }

    public T get(int index) {
        int i = begin;
        for (int count = 0; count < index; count += 1) {
            i = (i + 1) % items.length;
        }
        return items[i];
    }
/*
    public static void main(String[] args) {
        ArrayDeque<Integer> L = new ArrayDeque<Integer>();
        for (int i = 10; i < 20; i += 1) {
            L.addLast(i);
        }
        L.printDeque();
        for (int i = 9; i >= 0; i -= 1) {
            L.addFirst(i);
        }
        L.printDeque();
        for (int i = 20; i < 35; i += 1) {
            L.addLast(i);
        }
        L.printDeque();
        for (int i = 0; i < 35; i += 1) {
            L.removeLast();
        }
    }

 */
}
