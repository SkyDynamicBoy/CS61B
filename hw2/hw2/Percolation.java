package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import org.junit.Test;
import static org.junit.Assert.*;

public class Percolation {
    private int size;
    private int openNums;
    private WeightedQuickUnionUF sites;
    private boolean[][] isopen;

    private int[] openBottomSitesNumber;
    private int openBottomNums;
    private boolean isPercolation;
    private int totalsites;


    private int xyTo1D(int row, int col) {
        return row * size + col;
    }
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.size = N;
        this.totalsites = N *N;
        this.sites = new WeightedQuickUnionUF(totalsites + 1);
        this.isopen = new boolean[N][N];
        this.openBottomSitesNumber = new int[N];
        this.openBottomNums = 0;
        this.openNums = 0;
        this.isPercolation = false;

        for(int i = 0; i < N; i++) {
            sites.union(i, N * N);
        }
    }                // create N-by-N grid, with all sites initially blocked


    public void open(int row, int col) {
        if (col < 0 || col >= size || row < 0 || row >= size) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        if (isopen[row][col]) {
            return;
        }
        isopen[row][col] = true;
        openNums++;
        makeUnionWithNeibor(row, col);

        int lastindex = size - 1;
        if (row == lastindex) {
            openBottomSitesNumber[openBottomNums] = col;
            openBottomNums++;
        }
        if (!isPercolation) {
            for (int i = 0; i < openBottomNums; i++) {
                if (sites.connected(xyTo1D(lastindex, openBottomSitesNumber[i]), totalsites)) {
                    isPercolation = true;
                    break;
                }
            }
        }
    }       // open the site (row, col) if it is not open already

    private void makeUnionWithNeibor(int row, int col) {
        int index = xyTo1D(row, col);
        int lastrow = row - 1;
        if (lastrow >= 0 && isopen[lastrow][col]) {
            sites.union(xyTo1D(lastrow, col), index);
        }
        int nextrow = row + 1;
        if (nextrow < size && isopen[nextrow][col]) {
            sites.union(xyTo1D(nextrow, col), index);
        }
        int lastcol = col - 1;
        if (lastcol >= 0 && isopen[row][lastcol]) {
            sites.union(xyTo1D(row, lastcol), index);
        }
        int nextcol = col + 1;
        if (nextcol < size && isopen[row][nextcol]) {
            sites.union(xyTo1D(row, nextcol), index);
        }
    }


    public boolean isOpen(int row, int col) {
        if (col < 0 || col >= size || row < 0 || row >= size) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return isopen[row][col];
    }  // is the site (row, col) open?


    public boolean isFull(int row, int col) {
        if (col < 0 || col >= size || row < 0 || row >= size) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return sites.connected(xyTo1D(row, col), totalsites) && isOpen(row, col);
    } // is the site (row, col) full?


    public int numberOfOpenSites() {
        return openNums;
    }           // number of open sites


    public boolean percolates() {
        return isPercolation;
    }             // does the system percolate?

    @Test
    private static void main(String[] args) {
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

