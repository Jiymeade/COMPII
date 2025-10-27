import javax.swing.*;

public class GameController {
 private TicTacToeGame game;
 private TTTFrame frame;


 public GameController(TicTacToeGame game, TTTFrame frame) {
     this.game = game;
     this.frame = frame;

 }
 public void Move(TTTTileButton button) {
     int row= button.getRow();
     int col = button.getCol();

     if (!game.makeMove(row, col)) {
         JOptionPane.showMessageDialog(frame, "Illegal move! Tile already occupied!");
         return;
     }
     button.setText(game.getCurrentPlayer().getSymbol());
     if(game.checkWin()){
         JOptionPane.showMessageDialog(frame, game.getCurrentPlayer().getName() + " wins!");

         int response = JOptionPane.showConfirmDialog(frame, "Play again?", "Tic-Tac-Toe", JOptionPane.YES_NO_OPTION);
         if(response == JOptionPane.YES_OPTION){
             game.resetBoard();
             frame.resetBoard();
         }else{
             System.exit(0);
         }
         return;

     }
     if(game.checkTie()){
         JOptionPane.showMessageDialog(frame,  "Tie");

         int response = JOptionPane.showConfirmDialog(frame, "Play again?", "Tic-Tac-Toe", JOptionPane.YES_NO_OPTION);
         if(response == JOptionPane.YES_OPTION){
             game.resetBoard();
             frame.resetBoard();
         }else{
             System.exit(0);
         }
         return;

     }
     game.nextPlayer();

 }
}
