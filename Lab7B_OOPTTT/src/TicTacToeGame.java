public class TicTacToeGame {
private TTTBoard board;
public Player currentPlayer;
public Player playerX;
public Player playerO;

public TicTacToeGame(String playerXName, String playerOName) {
    board = new TTTBoard();
    playerX = new Player(playerXName, "X");
    playerO = new Player(playerOName, "O");
    currentPlayer = playerX;
}
public boolean makeMove(int row, int col) {
    if(!board.isEmpty(row,col))return false;
    board.setTiles(row,col, currentPlayer.getSymbol());
    return true;
}
public boolean checkWin(){
    return board.checkWin(currentPlayer.getSymbol());
}
public boolean checkTie(){
    return board.checkTie();
}
public void nextPlayer(){
    currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
}
public Player getCurrentPlayer(){
    return currentPlayer;
}
public TTTBoard getBoard(){
    return board;
}
public void resetBoard(){
    board.clear();
    currentPlayer = playerX;
}
}
