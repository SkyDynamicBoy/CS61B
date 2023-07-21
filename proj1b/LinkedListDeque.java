public class LinkedListDeque<T> implements Deque<T> {
    private InNode sentinel;
    private int size;

    //List Node
    private class InNode {
        private T item;
        private InNode next;
        private InNode before;

        public InNode(T item, InNode before, InNode next) {
            this.item = item;
            this.before = before;
            this.next = next;
        }
    }


    // Constructor
    public LinkedListDeque() {
        sentinel = new InNode(null, null, null);
        sentinel.before = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    // add an element at first
    @Override
    public void addFirst(T item) {
        InNode oldfirst = sentinel.next;
        sentinel.next = new InNode(item, sentinel, oldfirst);
        oldfirst.before = sentinel.next;
        size += 1;
    }

    // add an element at last
    @Override
    public void addLast(T item) {
        InNode oldlast = sentinel.before;
        sentinel.before = new InNode(item, sentinel.before, sentinel);
        oldlast.next = sentinel.before;
        size += 1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of elements
    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        InNode ptr = sentinel.next;
        while (ptr != sentinel) {
            System.out.print(ptr.item + " ");
            ptr = ptr.next;
        }
    }

    // remove an element at first
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            System.out.println("no elements!");
            return null;
        }
        InNode tmp = sentinel.next;
        sentinel.next = sentinel.next.next;
        sentinel.next.before = sentinel;
        size -= 1;
        return tmp.item;
    }

    // remove an element at last
    @Override
    public T removeLast() {
        if (isEmpty()) {
            System.out.println("no elements!");
            return null;
        }
        InNode tmp = sentinel.before;
        sentinel.before = sentinel.before.before;
        sentinel.before.next = sentinel;
        size -= 1;
        return tmp.item;
    }

    // get the item with index
    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            System.out.println("index ot of range");
            return null;
        }
        InNode ptr = sentinel;
        while (index >= 0) {
            ptr = ptr.next;
            index--;
        }
        return ptr.item;
    }

    public T getRecursive(int index) {
        if (index >= size || index < 0) {
            System.out.println("index ot of range");
            return null;
        }
        return getRecursiveHelper(index, sentinel.next);
    }

    private T getRecursiveHelper(int index, InNode ptr) {
        if (index == 0) {
            return ptr.item;
        }
        return getRecursiveHelper(index - 1, ptr.next);
    }
}


