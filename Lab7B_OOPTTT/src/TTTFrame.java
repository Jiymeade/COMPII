import javax.swing.*;
import java.awt.*;

public class TTTFrame extends JFrame {
    private TTTTileButton[][] buttons;
    private GameController controller;
    private TicTacToeGame game;
    public TTTFrame() {
        game = new TicTacToeGame("Player X", "Player O");
        controller = new GameController(game, this);

        setLayout(new BorderLayout());
        buttons = new TTTTileButton[3][3];
        JPanel boardPanel = new JPanel(new GridLayout(3, 3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                TTTTileButton button = new TTTTileButton(i, j);
                button.addActionListener(e -> controller.Move(button));
                buttons[i][j] = button;
                boardPanel.add(button);
            }
        }
        add(boardPanel, BorderLayout.CENTER);
        JButton quit = new JButton("Quit");
        quit.addActionListener(e -> System.exit(0));
        add(quit, BorderLayout.SOUTH);

        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


        public void resetBoard(){
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    buttons[i][j].setText("");
                }
            }
        }


    }

