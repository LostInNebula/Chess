
public class Knight {
    private int row;
    private int col; 
    private boolean isBlack;

    public Knight(int row, int col, boolean isBlack){
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol){
        if(board.verifySourceAndDestination(this.row, this.col, endRow, endCol, isBlack) == true){
            // furthest forward and to the left
            if(endRow == (this.row - 2) && endCol == (this.col - 1)){
                return true;
            }
            
            // least furthest forward and to the left
            if(endRow == (this.row - 1) && endCol == (this.col - 2)){
                return true;
            }

            // furthest forward and to the right
            if(endRow == (this.row - 2) && endCol == (this.col + 1)){
                return true;
            }

            // least furthest forward and to the right
            if(endRow == (this.row - 1) && endCol == (this.col + 2)){
                return true;
            }

            // least furthest backward and to the left
            if(endRow == (this.row + 1) && endCol == (this.col - 2)){
                return true;
            }

            // furthest backward and to the left
            if(endRow == (this.row + 2) && endCol == (this.col - 1)){
                return true;
            }
            
            // least furthest backward and to the right
            if(endRow == (this.row + 1) && endCol == (this.col + 2)){
                return true;
            }

            // furthest backward and to the right
            if(endRow == (this.row + 2) && endCol == (this.col + 1)){
                return true;
            }

            return false;
        }
        return false;
    }
}
