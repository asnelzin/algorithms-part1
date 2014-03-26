public class Solver {
    private SearchNode result;
    
    private class SearchNode implements Comparable<SearchNode> {
        private final Board board;
        private final SearchNode prev;
        private final int moves;
        private final int priority;
        
        private SearchNode(Board b, SearchNode p) {
            board = b;
            prev = p;
            if (prev == null)
                moves = 0;
            else
                moves = prev.moves + 1;
            priority = board.manhattan() + moves;
        }

        public int compareTo(SearchNode that) {
            return this.priority - that.priority;
        }
    } 

    public Solver(Board initial) {
        if (initial.isGoal())
            result = new SearchNode(initial, null);
        else
            result = solve(initial, initial.twin());
    }
    
    private SearchNode solve(Board initial, Board twin) {
        MinPQ<SearchNode> mainpq = new MinPQ<SearchNode>();
        MinPQ<SearchNode> twinpq = new MinPQ<SearchNode>();
        SearchNode last;

        mainpq.insert(new SearchNode(initial, null));
        twinpq.insert(new SearchNode(twin, null));
        
        while (true) {
            if (step(mainpq))
                return mainpq.min();
            if (step(twinpq))
                return null;
        }

    }

    private boolean step(MinPQ<SearchNode> pq) {
        if (pq.min().board.isGoal())
            return true;
        else {
            SearchNode least = pq.delMin();
            for (Board neighbor : least.board.neighbors()) {
                if (least.prev == null || !neighbor.equals(least.prev.board))
                    pq.insert(new SearchNode(neighbor, least));
            }
        }
        return false;
    }

    public boolean isSolvable() {
        return result != null;
    }

    public int moves() {
        if (isSolvable())
            return result.moves;
        return -1;
    }

    public Iterable<Board> solution() {
        if (isSolvable()) {
            Stack<Board> s = new Stack<Board>();
            for (SearchNode n = result; n != null; n = n.prev)
                s.push(n.board);
            return s;
        }
        return null;


    }

    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
        // for (Board neighbor : initial.neighbors())
        //     StdOut.println(neighbor);

        //solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}