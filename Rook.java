
public class Rook {

    private int row;
    private int col; 
    private boolean isBlack;

    public Rook(int row, int col, boolean isBlack){
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol){
       if(board.verifySourceAndDestination(this.row, this.col, endRow, endCol, isBlack)){

        // as long as the piece is moving strictly horizontally or vertically the move is valid
           if(this.row == endRow && this.col != endCol){
                return board.verifyHorizontal(this.row, this.col, endRow, endCol);
            }

           if(this.row != endRow && this.col == endCol){
                return board.verifyVertical(this.row, this.col, endRow, endCol); 
            }
       }
       return false;
    }
}


