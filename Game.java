// Written by Gresham Basic, basic009
import java.util.Scanner;
public class Game {


    public static void main(String[] args) {
        boolean validTurn = false;
        boolean gameOver = false;
        String turnColor = "white";
        Board myBoard = new Board();
        Scanner moveScanner = new Scanner(System.in);
        //Fen.load("r1bk3r/p2pBpNp/n4n2/1p1NP2P/6P1/3P4/P1P1K3/q5b1", myBoard);
        Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", myBoard);

        // mechanism for turn taking until a king is captured
        while(gameOver == false){

            // white turn
            if(turnColor.equals("white")){
                validTurn = false;
                while(validTurn == false){
                    System.out.println(myBoard);
                    System.out.println("it is currently white's turn");
                    System.out.println("what is the row of your selected piece?");
                    int startRow = moveScanner.nextInt();
                    System.out.println("what is the column of your selected piece?");
                    int startCol = moveScanner.nextInt();
                    System.out.println("what is the row of your desired location?");
                    int endRow = moveScanner.nextInt();
                    System.out.println("what is the column of your desired location?");
                    int endColumn = moveScanner.nextInt();
                    if(myBoard.verifySourceAndDestination(startRow, startCol, endRow, endColumn, false) == true){
                        if((myBoard.getPiece(startRow, startCol)).isMoveLegal(myBoard, endRow, endColumn)){
                            myBoard.movePiece(startRow, startCol, endRow, endColumn);
                            if(myBoard.isGameOver()){
                                gameOver = true;
                                break;
                            }
                            System.out.println(myBoard);
                            turnColor = "black";
                            validTurn = true;
                            break;
                        }
                    }
                }
            }
            
            // black turn
            if(turnColor.equals("black")){
                validTurn = false;
                while(validTurn == false){
                    System.out.println(myBoard);
                    System.out.println("it is currently black's turn");
                    System.out.println("what is the row of your selected piece?");
                    int startRow = moveScanner.nextInt();
                    System.out.println("what is the column of your selected piece?");
                    int startCol = moveScanner.nextInt();
                    System.out.println("what is the row of your desired location?");
                    int endRow = moveScanner.nextInt();
                    System.out.println("what is the column of your desired location?");
                    int endColumn = moveScanner.nextInt();
                    if(myBoard.verifySourceAndDestination(startRow, startCol, endRow, endColumn, true) == true){
                        if((myBoard.getPiece(startRow, startCol)).isMoveLegal(myBoard, endRow, endColumn)){
                            myBoard.movePiece(startRow, startCol, endRow, endColumn);
                            if(myBoard.isGameOver()){
                                gameOver = true;
                                break;
                            }
                            System.out.println(myBoard);
                            turnColor = "white";
                            validTurn = true;
                            break;
                        }
                    }
                }
            }
        }
        
        moveScanner.close();
    }
}

// doesnt know what to do when invalid, it is breaking after a turn, it isn't switching who goes next