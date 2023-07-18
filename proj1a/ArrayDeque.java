public class ArrayDeque<T> {
    private int size;
    private int capacity;
    private int nextFirst;
    private int nextLast;
    private T[] arr;


    private static final int INIT_CAPACITY = 8;
    private static final int INIT_NEXT_FIRST = 3;
    private static final int INIT_NEXT_LAST = 4;

    // Constructor
    public ArrayDeque() {
        arr = (T[]) new Object[INIT_CAPACITY];
        capacity = INIT_CAPACITY;
        nextFirst = INIT_NEXT_FIRST;
        nextLast = INIT_NEXT_LAST;
        size = 0;
    }

    public void addFirst(T item) {
        if (size == capacity) {
            addCapacity();
        }
        arr[nextFirst] = item;
        nextFirst = circularMinus(nextFirst);
        size += 1;
    }

    public void addLast(T item) {
        if (size == capacity) {
            addCapacity();
        }
        arr[nextLast] = item;
        nextLast = circularPlus(nextLast);
        size += 1;
    }

    // Helper to double the capacity
    private void addCapacity() {
        capacity = capacity * 2;
        T[] newarr = (T[]) new Object[capacity];
        if (nextLast == 0) {
            System.arraycopy(arr, 0, newarr, 0, size);
            nextLast = nextLast + size;
        } else {
            for (int i = 0; i < nextLast; i++) {
                newarr[i] = arr[i];
            }
            for (int i = size - 1; i > nextFirst; i--) {
                newarr[i + size] = arr[i];
            }
        }
        nextFirst = nextFirst + size;
        arr = newarr;
    }

    //Helper to minus a index
    private int circularMinus(int num) {
        num = num - 1;
        if (num == -1) {
            num = capacity - 1;
        }
        return num;
    }

    //Helper to plus a index
    private int circularPlus(int num) {
        num = num + 1;
        if (num == capacity) {
            num = 0;
        }
        return num;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int firstindex = circularPlus(nextFirst);
        for (int i = firstindex; i != nextLast; i = circularPlus(i)) {
            System.out.print(arr[i] + " ");
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            System.out.println("no elements!");
            return null;
        }
        nextFirst = circularPlus(nextFirst);
        size -= 1;
        T result = arr[nextFirst];
        if (size == capacity / 2) {
            shrink();
        }
        return result;
    }

    public T removeLast() {
        if (isEmpty()) {
            System.out.println("no elements!");
            return null;
        }
        nextLast = circularMinus(nextLast);
        size -= 1;
        T result = arr[nextLast];
        if (size == capacity / 2) {
            shrink();
        }
        return result;
    }

    private void shrink() {
        int oldcapacity = capacity;
        capacity = size;
        T[] newarr = (T[]) new Object[capacity];
        if (nextLast > nextFirst) {
            System.arraycopy(arr, nextFirst + 1, newarr, 0, size);
        } else {
            int j = 0;
            for (int i = nextFirst + 1; i < oldcapacity; i++, j++) {
                newarr[j] = arr[i];
            }
            for (int i = 0; i < nextLast; i++, j++) {
                newarr[j] = arr[i];
            }
        }
        nextLast = 0;
        nextFirst = size - 1;
        arr = newarr;
    }

    public T get(int index) {
        if (index >= size || index < 0) {
            System.out.println("index ot of range");
            return null;
        }
        int realIndex = (nextFirst + index + 1) % capacity ;
        return arr[realIndex];
    }

}

