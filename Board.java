// Written by Gresham Basic, basic009
import java.util.Scanner;
public class Board {

    // Instance variables
    private Piece[][] board;

    //TODO:
    // Construct an object of type Board using given arguments.
    public Board() {
        board = new Piece[8][8];
    }

    // Accessor Methods

    //TODO:
    // Return the Piece object stored at a given row and column
    public Piece getPiece(int row, int col) {
        return board[row][col];
    }

    //TODO:
    // Update a single cell of the board to the new piece.
    public void setPiece(int row, int col, Piece piece) {
        board[row][col] = piece;
    }

    // Game functionality methods

    //TODO:
    // Moves a Piece object from one cell in the board to another, provided that
    // this movement is legal. Returns a boolean to signify success or failure.
    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
        Piece currentPiece = board[startRow][startCol];
        if(currentPiece == null){
            return false;
        }
        if(currentPiece.isMoveLegal(this, endRow, endCol)){
            // white pawn promotion
            if((currentPiece.getCharacter() == '\u2659' && endRow == 7)){
                Scanner choiceScanner = new Scanner(System.in);
                System.out.println("your pawn shall be promoted, what do you want it to become?");
                String choice  = choiceScanner.nextLine();
                switch(choice){
                    case "rook":
                        currentPiece = new Piece('\u2656', endRow, endCol, false);
                        break;
                    case "bishop":
                        currentPiece = new Piece('\u2657', endRow, endCol, false);
                        break;
                    case "knight":
                        currentPiece = new Piece('\u2658', endRow, endCol, false);
                        break;
                }
                
                choiceScanner.close();

            }
            //black pawn promotion
            if((currentPiece.getCharacter() == '\u265F' && endRow == 0)){
                Scanner choiceScanner = new Scanner(System.in);
                System.out.println("your pawn shall be promoted, what do you want it to become?");
                String choice  = choiceScanner.nextLine();
                switch(choice){
                    case "rook":
                        currentPiece = new Piece('\u265C', endRow, endCol, false);
                        break;
                    case "bishop":
                        currentPiece = new Piece('\u265D', endRow, endCol, false);
                        break;
                    case "knight":
                        currentPiece = new Piece('\u265E', endRow, endCol, false);
                        break;
                }
                
                choiceScanner.close();
                
            }

            board[endRow][endCol] = currentPiece;
            board[startRow][startCol] = null;
            return true;
        }
        return false;
    }

    //TODO:
    // Determines whether the game has been ended, i.e., if one player's King
    // has been captured.
    public boolean isGameOver() {
        int kingCount = 0;
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
				if(board[i][j] != null){
					if(board[i][j].getCharacter() == '\u265a' || board[i][j].getCharacter() == '\u2654'){
						kingCount += 1;
					}
                }
            }
        }
        if (kingCount < 2){
            return true;
        } else {
            return false;
        }
    }

    //TODO:
    // Construct a String that represents the Board object's 2D array. Return
    // the fully constructed String.
    public String toString() {
    	String string = "  \u2001\uFF10\uFF11\uFF12\uFF13\uFF14\uFF15\uFF16\uFF17\n";
		for(int i = 0; i < 8; i++){
			if(i < 7){
				string += this.rowToString(i);
				string += "\n";
		   } else {
			   string += this.rowToString(i);
		   }
	   }
        return string; 
    }

    //TODO:
    // Sets every cell of the array to null. For debugging and grading purposes.
    public void clear() {
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                board[i][j] = null;
            }
        }

    }

    // Movement helper functions

    //TODO:
    // Ensure that the player's chosen move is even remotely legal.
    // Returns a boolean to signify whether:
    // - 'start' and 'end' fall within the array's bounds.
    // - 'start' contains a Piece object, i.e., not null.
    // - Player's color and color of 'start' Piece match.
    // - 'end' contains either no Piece or a Piece of the opposite color.
    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        int startSum = startRow + startCol;
        int endSum = endRow + endCol;
        if(startSum < 0 || startSum > 14){
            return false;
        }

        if(endSum < 0 || endSum > 14){
            return false;
        }
        
        if(board[startRow][startCol] == null){
            return false;
        }

        if(board[startRow][startCol].getIsBlack() != isBlack){
            return false;
        }

        if(board[endRow][endCol] != null){
            if(board[endRow][endCol].getIsBlack() == isBlack){
                return false;
            }
        }
        return true;
    }

    //TODO:
    // Check whether the 'start' position and 'end' position are adjacent to each other
    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        if(startRow == endRow){
            if(Math.abs(startCol - endCol) == 1){
                return true;
            }
        }

        if(startCol == endCol){
            if(Math.abs(startRow - endRow) == 1){
                return true;
            }
        }
        return false;
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid horizontal move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one row.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        if(startRow != endRow){
            return false;
        }

        if(startCol > endCol){
            for(int i = endCol + 1; i < startCol; i++){
                if(board[startRow][i] != null){
                    return false;
                }
            }
        }

        if(endCol > startCol){
            for(int i = startCol + 1; i < endCol; i++){
                if(board[startRow][i] != null){
                    return false;
                }
            }
        }
        return true;
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid vertical move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one column.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        if(startCol != endCol){
            return false;
        }
        
        if(startRow > endRow){
            for(int i = endRow + 1; i < startRow; i++){
                if(board[i][startCol] != null){
                    return false;
                }
            }
        }

        if(endRow > startRow){
            for(int i = startRow + 1; i < endRow; i++){
                if(board[i][startCol] != null){
                    return false;
                }
            }
        }

        return true; 
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid diagonal move.
    // Returns a boolean to signify whether:
    // - The path from 'start' to 'end' is diagonal... change in row and col.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {

        // any square that is diagnol to another will have a slope of either -1 or 1 relative to
        // the initial square, thus by using (y1 - y2)/(x1 - x2) we can calculate the slope between
        // two squares on the board

        int colDiff = startCol - endCol;
        int rowDiff = startRow - endRow;
        int slope = Math.abs(rowDiff / colDiff);
        if (slope != 1){
            return false;
        }
        // moving piece down and to the right
        if (colDiff < 0 && rowDiff < 0){
            for(int i = 1 ; i < Math.abs(colDiff); i++){
                if(board[startRow + i][startCol + i] != null){
                    return false;
                }                
            }
        }
        // moving piece down and to the left
        if (colDiff > 0 && rowDiff < 0){
            for(int i = 1 ; i < Math.abs(colDiff); i++){
                if(board[startRow + i][startCol - i] != null){
                    return false;
                }                
            }

        }

        // moving piece up and to the right
        if (colDiff < 0 && rowDiff > 0){
            for(int i = 1 ; i < Math.abs(colDiff); i++){
                if(board[startRow - i][startCol + i] != null){
                    return false;
                }                
            }

        }

        // moving piece up and to the left
        if (colDiff > 0 && rowDiff > 0){
            for(int i = 1 ; i < Math.abs(colDiff); i++){
                if(board[startRow - i][startCol - i] != null){
                    return false;
                }                
            }
        }
        return true;
    }


	public String rowToString(int rowNumber){
		char rowNum = '\0';
		String rowString = "";
		switch(rowNumber){
			case 0:
				rowNum = '\uFF10';
				break;
			case 1:
				rowNum = '\uFF11';
				break;
			case 2:
				rowNum = '\uFF12';
				break;
			case 3:
				rowNum = '\uFF13';
				break;
			case 4:
				rowNum = '\uFF14';
				break;
			case 5:
				rowNum = '\uFF15';
				break;
			case 6:
				rowNum = '\uFF16';
				break;
			case 7:
				rowNum = '\uFF17';
				break;
		}
		rowString += rowNum + "|";
		for(int i = 0; i < 8; i++){
			if (board[rowNumber][i] == null){
				rowString += "\u2001" + "|";
			} else {
				rowString += (board[rowNumber][i]).getCharacter() + "|";
			}
		}
		return rowString;
	}
}
