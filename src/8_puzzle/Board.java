import java.util.Arrays;

public class Board {
    private final int[][] board;
    private final int dim;
    private int cachedManhattan = -1;
    
    public Board(int[][] blocks) {
        dim = blocks.length;
        board = clone(blocks);
    }

    public int dimension(){
        return dim;
    }
    
    public int hamming() {
        int hamm = 0;
        int value = 1;
        for (int i = 0; i < dim; i++)
            for (int j = 0; j < dim; j++)
                if (board[i][j] != value++)
                    hamm++;
        return hamm - 1;
    }

    public int manhattan() {
        if (cachedManhattan >= 0)
            return cachedManhattan;
        int value, goalRow, goalCol;
        int manh = 0;
        for (int row = 0; row < dim; row++)
            for (int col = 0; col < dim; col++) {
                value = board[row][col];
                if (value != 0) {
                    goalRow = (value - 1) / dim;
                    goalCol = (value - 1) % dim;
                    manh += Math.abs(goalRow - row) + Math.abs(goalCol - col);
                }
            }
        return manh;
    }
    
    public boolean isGoal() {
        if (board[dim - 1][dim - 1] != 0)
            return false;
        int value = 1;
        for (int row = 0; row < dim; row++)
            for (int col = 0; col < dim; col++) {
                if (row == dim - 1 && col == dim - 1)
                    return true;
                if (board[row][col] != value++)
                    return false;
            }
        return true;
    }
    
    public Board twin() {
        int[][] copy = clone(board);
        if (dim <= 1)
            return new Board(copy);

        int row = 0, col = 1, value = 0, prevValue = 0;
        findtwin:
        for (row = 0; row < dim; row++)
            for (col = 1; col < dim; col++) {
                value = copy[row][col];
                prevValue = copy[row][col - 1];
                if (value != 0 && prevValue != 0)
                    break findtwin;
            }

        copy[row][col] = prevValue;
        copy[row][col - 1] = value;
        return new Board(copy);
    }

    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (this.getClass() != y.getClass()) return false;
        
        Board that = (Board) y;
        if (this.board.length != that.board.length) return false;
        
        for (int row = 0; row < dim; row++)
            if (!Arrays.equals(this.board[row], that.board[row]))
                return false;
        return true;
    }

    public Iterable<Board> neighbors() {
        Bag<Board> b = new Bag<Board>();

        int row = 0, col = 0;
        findzero:
        for (row = 0; row < dim; row++)
            for (col = 0; col < dim; col++)
                if (board[row][col] == 0)
                    break findzero;

        //StdOut.println(row + " " + col);

        //top
        if (row > 0)
            b.add(new Board(swap(row, col, row - 1, col)));
        //right
        if (col < dim - 1)
            b.add(new Board(swap(row, col, row, col + 1)));
        //bot
        if (row < dim - 1)
            b.add(new Board(swap(row, col, row + 1, col)));
        //left
        if (col > 0)
            b.add(new Board(swap(row, col, row, col - 1)));

        return b;
    }
    
    public String toString() {
        String out = "";
        out += dim + "\n";
        for (int i = 0; i < dim; i++) {
            out += " ";
            for (int j = 0; j < dim; j++) {
                out += board[i][j] + "  ";
            }
            out += "\n";
        }
        return out;
    }

    private int[][] swap(int fromRow, int fromCol, int toRow, int toCol) {
        int[][] copy = clone(board);

        int temp = copy[fromRow][fromCol];
        copy[fromRow][fromCol] = copy[toRow][toCol];
        copy[toRow][toCol] = temp;

        return copy;
    }

    private int[][] clone(int[][] blocks) {
        int N = blocks.length;
        int[][] copy = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                copy[i][j] = blocks[i][j];
        return copy;
    }
}