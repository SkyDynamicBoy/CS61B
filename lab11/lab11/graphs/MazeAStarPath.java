package lab11.graphs;


import edu.princeton.cs.algs4.Stack;
import java.lang.Comparable;
import java.util.PriorityQueue;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }



    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        int vx = maze.toX(v);
        int vy = maze.toY(v);
        int tx = maze.toX(t);
        int ty = maze.toY(t);
        return Math.abs(tx - vx) + Math.abs(ty - vy);
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return  -1;
        /* You do not have to use this method. */
    }

    private class Node implements Comparable<Node> {
        private int v;
        private int weight;

        public Node(int v) {
            this.v = v;
            this.weight = h(v) + distTo[v];
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }

        public int V() {
            return this.v;
        }
    }


    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        PriorityQueue<Node> addToPath = new PriorityQueue<>();
        addToPath.add(new Node(s));

        while (!addToPath.isEmpty()) {
            int v = addToPath.remove().V();
            marked[v] = true;
            announce();
            if (v == t) {
                targetFound = true;
            }
            if (targetFound) {
                return;
            }
            for (int w : maze.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    addToPath.add(new Node(w));
                }
            }
        }
    }

    @Override
    public void solve() {
        astar(s);
    }

}

