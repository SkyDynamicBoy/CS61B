package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState {
    private final int size;
    private final int[][] tiles;
    private static final int BLANK = 0;

    public Board(int[][] tiles) {
        this.size = tiles.length;
        this.tiles = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }

    public int tileAt(int i, int j) {
        if (i >= size || i < 0 || j >= size || j < 0) {
            throw new IndexOutOfBoundsException("index must between 0 and size - 1!");
        }
        return tiles[i][j];
    }

    public int size() {
        return size;
    }


    /**
     * source http://joshh.ug/neighbors.html
     */
    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    public int hamming() {
        int goal = 1;
        int num = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tileAt(i, j) == 0 && goal != size * size) {
                    num++;
                } else if (tileAt(i, j) != goal) {
                    num++;
                }
                goal++;
            }
        }
        return num;
    }


    public int manhattan() {
        int manhattanSum = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int c = tileAt(i, j) - 1;
                if (c != -1) {
                    int y = c % size;
                    int x = c / size;
                    manhattanSum += Math.abs(i - x) + Math.abs(j - y);
                }
            }
        }
        return manhattanSum;
    }

    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    @Override
    public boolean equals(Object y) {
        if (y == null) {
            return false;
        }
        if (this == y) {
            return true;
        }
        if (this.getClass() != y.getClass()) {
            return false;
        }
        Board o = (Board) y;
        if (this.size != o.size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (this.tileAt(i, j) != o.tileAt(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public int hasCode() {
        return super.hashCode();
    }

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
