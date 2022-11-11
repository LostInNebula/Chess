
// Written by Gresham Basic, basic009
public class Bishop {
    private int row;
    private int col; 
    private boolean isBlack;

    public Bishop(int row, int col, boolean isBlack){
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol){
        if(board.verifySourceAndDestination(this.row, this.col, endRow, endCol, isBlack) == true){
            return board.verifyDiagonal(this.row, this.col, endRow, endCol);
        }
        return false;
    }
}