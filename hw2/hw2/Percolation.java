package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import org.junit.Test;
import static org.junit.Assert.*;

public class Percolation {
    private int size;
    private WeightedQuickUnionUF sites;
    private boolean[] states;
    private int openedNumber;


    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.size = N;
        this.sites = new WeightedQuickUnionUF(N * N);
        this.states = new boolean[N * N];
        this.openedNumber = 0;
    }                // create N-by-N grid, with all sites initially blocked


    public void open(int row, int col) {
        if (col < 0 || col >= size || row < 0 || row >= size) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        int index = row * size + col;
        if (states[index]) {
            return;
        }
        states[index] = true;
        openedNumber++;
        connectOpen(index, row, col);
    }       // open the site (row, col) if it is not open already

    private void connectOpen(int index, int row, int col) {
        int lastrow = row - 1;
        if (lastrow >= 0) {
            int slideup = lastrow * size + col;
            if (states[slideup]) {
                sites.union(slideup, index);
            }
        }
        int nextrow = row + 1;
        if (nextrow < size) {
            int slidedown = nextrow * size + col;
            if (states[slidedown]) {
                sites.union(slidedown, index);
            }
        }
        int lastcol = col - 1;
        if (lastcol >= 0) {
            int slideleft = row * size + lastcol;
            if (states[slideleft]) {
                sites.union(slideleft, index);
            }
        }
        int nextcol = col + 1;
        if (nextcol < size) {
            int slideright = row * size + nextcol;
            if (states[slideright]) {
                sites.union(slideright, index);
            }
        }
    }


    public boolean isOpen(int row, int col) {
        if (col < 0 || col >= size || row < 0 || row >= size) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        int index = row * size + col;
        return states[index];
    }  // is the site (row, col) open?


    public boolean isFull(int row, int col) {
        if (col < 0 || col >= size || row < 0 || row >= size) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        int index = row * size + col;
        if (!states[index]) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (states[i] && sites.connected(i, index)) {
                return true;
            }
        }
        return false;
    } // is the site (row, col) full?


    public int numberOfOpenSites() {
        return openedNumber;
    }           // number of open sites


    public boolean percolates() {
        int endIndex = size - 1;
        for (int i = 0; i < size; i++) {
            if (isFull(endIndex, i)) {
                return true;
            }
        }
        return false;
    }             // does the system percolate?

    @Test
    public static void main(String[] args) {
        Percolation mySys = new Percolation(5);
        assertEquals(0, mySys.numberOfOpenSites());

        mySys.open(3, 4);
        assertTrue(mySys.isOpen(3, 4));

        mySys.open(2, 4);
        mySys.open(1, 4);
        mySys.open(0, 4);
        assertEquals(4, mySys.numberOfOpenSites());
        assertTrue(mySys.isFull(3, 4));

        mySys.open(2, 2);
        assertTrue(mySys.isOpen(2, 2));
        assertFalse(mySys.isFull(2, 2));
        mySys.open(2, 3);
        assertTrue(mySys.isFull(2, 2));

        assertFalse(mySys.percolates());
        mySys.open(4, 2);
        assertFalse(mySys.percolates());
        mySys.open(4, 3);
        assertFalse(mySys.percolates());
        mySys.open(3, 3);
        assertEquals(9, mySys.numberOfOpenSites());
        assertTrue(mySys.percolates());
    }  // use for unit testing (not required)
}