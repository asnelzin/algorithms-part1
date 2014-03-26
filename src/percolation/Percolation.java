public class Percolation {
    private WeightedQuickUnionUF ufBoth;
    private WeightedQuickUnionUF ufTop;
    private boolean[] opened;
    private int gridSize;
    private int sitesCount;
    private int virtualTop;
    private int virtualBot;

    public Percolation(int N) {
        gridSize = N;
        sitesCount = N*N;
        virtualTop = N*N;
        virtualBot = N*N + 1;
        ufBoth = new WeightedQuickUnionUF(sitesCount + 2);
        ufTop = new WeightedQuickUnionUF(sitesCount + 1);
        opened = new boolean[sitesCount];
        for (int i = 0; i < sitesCount; i++)
            opened[i] = false;
    } 

    public void open(int i, int j) {
        checkInput(i, j);

        int thisIndex = getIndex(i, j);
        opened[thisIndex] = true;
            
        if ((j - 1 >= 1) && isOpen(i, j - 1)) { //left
            ufBoth.union(thisIndex, getIndex(i, j - 1));
            ufTop.union(thisIndex, getIndex(i, j - 1));
        }

        if ((i - 1 >= 1) && isOpen(i - 1, j)) { //top
            ufBoth.union(thisIndex, getIndex(i - 1, j));
            ufTop.union(thisIndex, getIndex(i - 1, j));
        }
            
        if ((j + 1 <= gridSize) && isOpen(i, j + 1)) { //right
            ufBoth.union(thisIndex, getIndex(i, j + 1));
            ufTop.union(thisIndex, getIndex(i, j + 1));
        }
            
        if ((i + 1 <= gridSize) && isOpen(i + 1, j)) { //bottom
            ufBoth.union(thisIndex, getIndex(i + 1, j));
            ufTop.union(thisIndex, getIndex(i + 1, j));
        }

        if (i == 1) { //top
            ufBoth.union(thisIndex, virtualTop);
            ufTop.union(thisIndex, virtualTop);
        }
        if (i == gridSize)
            ufBoth.union(thisIndex, virtualBot);
    }

    public boolean isOpen(int i, int j) {
        checkInput(i, j);

        return opened[getIndex(i, j)];
    }

    public boolean isFull(int i, int j) {
        checkInput(i, j);

        return ufTop.connected(getIndex(i, j), virtualTop);
    }

    public boolean percolates() {
        return ufBoth.connected(virtualTop, virtualBot);
    }

    private void checkInput(int i, int j) {
        if ((i < 1) || (i > gridSize) || (j < 1) || (j > gridSize))
            throw new java.lang.IndexOutOfBoundsException();
    }

    private int getIndex(int i, int j) {
        return (i - 1)*gridSize + (j - 1);
    } 
} 