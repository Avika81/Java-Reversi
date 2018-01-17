
package ReversiBase;

public class Pair {
    private int x, y;

    /**
     * This method returns the row of the pair.
     * @return row.
     */
    public int getRow()  {
        return this.x;
    }

    /**
     * This method returns the column of the pair.
     * @return column.
     */
    public int getCol()  {
        return this.y;
    }

    /**
     * Constructor for pair from row and column.
     * @param row row.
     * @param column column.
     */
    public Pair(int row, int column) {
        this.x = row;
        this.y = column;
    }
}
