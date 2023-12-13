package Sudoku;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class sudukoTest {
	 @Test
	    public void testSolveSudoku() {
	        int[][] solvablePuzzle = {
	            // Solvable puzzle
	            {0, 0, 8, 0, 0, 9, 0, 6, 2},
	            {0, 0, 0, 0, 0, 0, 0, 0, 5},
	            {1, 0, 2, 5, 0, 0, 0, 0, 0},
	            {0, 0, 0, 2, 1, 0, 0, 9, 0},
	            {0, 5, 0, 0, 0, 0, 6, 0, 0},
	            {6, 0, 0, 0, 0, 0, 0, 2, 8},
	            {4, 1, 0, 6, 0, 8, 0, 0, 0},
	            {8, 6, 0, 0, 3, 0, 1, 0, 0},
	            {0, 0, 0, 0, 0, 0, 4, 0, 0}
	        };

	        int[][] unsolvablePuzzle = {
	          // Introducing a conflict (8 is repeated in the last row)
	        		{0, 0, 8, 0, 0, 9, 0, 6, 2},
		            {0, 0, 0, 0, 0, 0, 0, 0, 5},
		            {1, 0, 2, 5, 0, 0, 0, 0, 0},
		            {0, 0, 0, 2, 1, 0, 0, 9, 0},
		            {0, 5, 0, 0, 0, 0, 6, 0, 0},
		            {6, 0, 0, 0, 0, 0, 0, 2, 8},
		            {4, 1, 0, 6, 0, 8, 0, 0, 0},
		            {8, 6, 0, 0, 3, 0, 1, 0, 0},
		            {0, 0, 0, 0, 0, 0, 4, 0, 4}//<---- en extra 4 hÃ¤r
	        };
	        
	        
	        int[][] emptyPuzzle = {
	  	          // Introducing a conflict (8 is repeated in the last row)
	  	        		{0, 0, 0, 0, 0, 0, 0, 0, 0},
	  		            {0, 0, 0, 0, 0, 0, 0, 0, 0},
	  		            {0, 0, 0, 0, 0, 0, 0, 0, 0},
	  		            {0, 0, 0, 0, 0, 0, 0, 0, 0},
	  		            {0, 0, 0, 0, 0, 0, 0, 0, 0},
	  		            {0, 0, 0, 0, 0, 0, 0, 0, 0},
	  		            {0, 0, 0, 0, 0, 0, 0, 0, 0},
	  		            {0, 0, 0, 0, 0, 0, 0, 0, 0},
	  		            {0, 0, 0, 0, 0, 0, 0, 0, 0}
	  	        };
	        
	        Sudoku solverForSolvable = new Sudoku();
	        Sudoku solverForUnsolvable = new Sudoku();
	        Sudoku solverForEmpty = new Sudoku();
	        solverForSolvable.setBoard(solvablePuzzle);
	        solverForUnsolvable.setBoard(unsolvablePuzzle);
	        solverForEmpty.setBoard(emptyPuzzle);
	        
	        assertTrue(solverForEmpty.solve());
	        assertTrue(solverForSolvable.solve());
	        assertFalse(solverForUnsolvable.solve());
	        
	    }

}