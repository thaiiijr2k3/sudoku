package Sudoku;

public class Sudoku implements SudokuSolver {
    private int[][] board;

    /**
     * Set sudoku board, numbers 1-9 are fixed values, 0 is unsolved.
     *
     * @param board a board to copy values from
     * @throws IllegalArgumentException if board is invalid, e.g. not 9x9
     */
    @Override
    public void setBoard(int[][] board) {
        if (board.length != 9 || board[0].length != 9) {
            throw new IllegalArgumentException("Invalid board size. Must be 9x9.");
        }

        /*this.board = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.board[i][j] = board[i][j];
            }
        }*/
        this.board = board;
    }

    @Override
    /**
     * Get a copy of the sudoku board.
     *
     * @return a copy of the sudoku board
     */
    public int[][] getBoard() {
    	/*int[][] copy = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                copy[i][j] = board[i][j];
            }
        }
        return copy;*/
    	return board;
    }

    @Override
    /**
     * Solve sudoku.
     *
     * @return true if a solution could be found
     */
    public boolean solve() {
        // Implement Sudoku solving algorithm here
        // You can use backtracking or any other suitable algorithm
        // Return true if a solution is found, false otherwise
        
     // Find the first empty cell
        int[] emptyCell = findEmptyCell();
        int row = emptyCell[0];
        int col = emptyCell[1];

        // If there are no empty cells, the board is solved
        if (row == -1 && col == -1) {
            return true;
        }

        // Try placing a number in the empty cell
        for (int num = 1; num <= 9; num++) {
            if (isLegal(row, col, num)) {
                // Place the number if it's legal
                board[row][col] = num;

                // Recursively try to solve the rest of the board
                if (solve()) {
                    return true; // If a solution is found, stop searching
                }

                // If placing the number doesn't lead to a solution, backtrack
                board[row][col] = 0;
            }
        }

        // No number from 1 to 9 can be placed in this cell, backtrack
        return false;
    }

    @Override
    /**
     * Check if digit is legal on the current board.
     *
     * @param row the row index
     * @param col the column index
     * @param nbr the digit to check
     * @return true if legal
     */
    public boolean isLegal(int row, int col, int nbr) {
        // Implement the logic to check if placing 'nbr' in the given row and column is legal
        // Return true if legal, false otherwise
    	
    	// Check if 'nbr' is not in the same row, column, or 3x3 subgrid
        return !usedInRow(row, nbr) && !usedInCol(col, nbr) && !usedInSubgrid(row - row % 3, col - col % 3, nbr);
    }
    
    
 // Helper method to check if 'num' is present in the specified row
    private boolean usedInRow(int row, int num) {
        for (int col = 0; col < 9; col++) {
            if (board[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    // Helper method to check if 'num' is present in the specified column
    private boolean usedInCol(int col, int num) {
        for (int row = 0; row < 9; row++) {
            if (board[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    // Helper method to check if 'num' is present in the 3x3 subgrid
    private boolean usedInSubgrid(int startRow, int startCol, int num) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row + startRow][col + startCol] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    // Helper method to find the first empty cell in the board
    private int[] findEmptyCell() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    return new int[]{row, col};
                }
            }
        }
        return new int[]{-1, -1}; // No empty cell found
    }

    @Override
    /**
     * Get number on the board.
     *
     * @param row the row index
     * @param col the column index
     * @return number on the board
     */
    public int get(int row, int col) {
        return board[row][col];
    }

    @Override
    /**
     * Set number on the board, numbers 1-9 are fixed values, 0 is unsolved.
     *
     * @param row the row index
     * @param col the column index
     * @param nbr the number to set
     */
    public void set(int row, int col, int nbr) {
        board[row][col] = nbr;
    }

    @Override
    /**
     * Clear the board.
     */
    public void clear() {
        board = new int[9][9];
    }
    
    
}