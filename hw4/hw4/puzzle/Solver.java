package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.*;


public class Solver {
    private MinPQ addToPath;
    private int moves;
    private Deque<WorldState> seq;

    private class Node implements Comparable<Node> {
        private WorldState word;
        private int moves;
        private int distToGoal;
        private Node parent;

        public Node(WorldState word, int moves, Node parent) {
            this.word = word;
            this.moves = moves;
            this.distToGoal = word.estimatedDistanceToGoal();
            this.parent = parent;
        }

        @Override
        public int compareTo(Node o) {
            int weight1 = this.moves + this.distToGoal;
            int weight2 = o.moves + o.distToGoal;
            return weight1 - weight2;
        }
    }


    public Solver(WorldState initial) {
        addToPath = new MinPQ();
        addToPath.insert(new Node(initial, 0, null));

        while (!addToPath.isEmpty()) {
            Node v = (Node) addToPath.delMin();
            if (v.distToGoal == 0) {
                seq = new ArrayDeque<>();
                this.moves = v.moves;
                while (v != null) {
                    seq.push(v.word);
                    v = v.parent;
                }
                return;
            }
            for (WorldState w : v.word.neighbors()) {
                if (v.parent == null || !v.parent.word.equals(w)) {
                    addToPath.insert(new Node(w, v.moves + 1, v));
                }
            }
        }
    }
    public int moves() {
        return moves;
    }

    public Iterable<WorldState> solution() {
        return seq;
    }
}
