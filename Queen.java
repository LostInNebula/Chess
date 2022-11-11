// Written by Gresham Basic, basic009
public class Queen {
    private int row;
    private int col; 
    private boolean isBlack;

    public Queen(int row, int col, boolean isBlack){
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol){
        if(board.verifySourceAndDestination(this.row, this.col, endRow, endCol, isBlack) == true){
            if(this.row == endRow && this.col != endCol){
                return board.verifyHorizontal(this.row, this.col, endRow, endCol);
            }
            if(this.row != endRow && this.col == endCol){
                return board.verifyVertical(this.row, this.col, endRow, endCol);
            }

            // if it is not moving strictly horizontally or strictly vertically, the only option
            // left for a legal move would be a diagonal move, so checking for that
            if(this.row != endRow && this.col != endCol){
                return board.verifyDiagonal(this.row, this.col, endRow, endCol);
            }
        }
        return false;
    }
}