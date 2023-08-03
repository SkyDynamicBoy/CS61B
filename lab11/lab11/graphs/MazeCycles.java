package lab11.graphs;

import edu.princeton.cs.algs4.Stack;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    private int s;
    private int[] myEdgeTo;
    private Maze maze;
    private Stack<Integer> addToPath;


    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        s = maze.xyTo1D(1, 1);
        myEdgeTo = new int[maze.V()];
        myEdgeTo[s] = s;
        distTo[s] =0;
        addToPath = new Stack();
        addToPath.push(s);
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        int v = addToPath.pop();
        marked[v] = true;
        announce();
        for (int w : maze.adj(v)) {
            if (!marked[w]) {
                myEdgeTo[w] = v;
                distTo[w] = distTo[v] + 1;
                addToPath.push(w);
            } else if (myEdgeTo[v] != w) {
                edgeTo[v] = w;
            }
            if (!addToPath.isEmpty()) {
                solve();
            }
        }
    }

    // Helper methods go here
}

