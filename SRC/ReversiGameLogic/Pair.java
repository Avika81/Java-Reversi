
package ReversiBase;

public class Pair {
    private int row, column;

    /**
     * This method returns the row of the pair.
     * @return row.
     */
    public int getRow()  {
        return this.row;
    }

    /**
     * This method returns the column of the pair.
     * @return column.
     */
    public int getCol()  {
        return this.column;
    }

    /**
     * Constructor for pair from row and column.
     * @param row row.
     * @param column column.
     */
    public Pair(int row, int column) {
        this.row = row;
        this.column = column;
    }


}
