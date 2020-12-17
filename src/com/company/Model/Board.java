package com.company.Model;

public class Board {

    private int rows;
    private int columns;

    Piece [][]ourBoard;

    public Piece[][] getOurBoard() {
        return ourBoard;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    /**
     *
     * @param colToAdd which column did the user try to add a piece to
     * @return
     *      -1: if unsuccessful
     *      an int of which row it was added to if successful
     */
    public int addPiece(int colToAdd, String color) {
        // within normal range
        if(colToAdd >= 0 && colToAdd < columns) {
            // we can add
            if(ourBoard[0][colToAdd] == null) {
                boolean addedThePiece = false;
                int addedRow = -1;
                for(int row = rows - 1; row >= 0; row--)
                    if(ourBoard[row][colToAdd] == null) {
                        ourBoard[row][colToAdd] = new Piece();
                        ourBoard[row][colToAdd].setColor(color);
                        addedThePiece = true;
                        addedRow = row;
                        break;
                    }
                return addedRow;
            } else {
                // that row is full
                System.err.println("This column is full, please choose another.");
                return -1;
            }
        } else {
            // outside normal range
            System.err.println("You are trying to add somewhere that is not supported.");
            return -1;
        }
    }

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        ourBoard = new Piece[rows][columns];
        for(int row = 0; row < rows; row++)
            for(int col = 0; col < columns; col++)
                ourBoard[row][col] = null;
    }
}
