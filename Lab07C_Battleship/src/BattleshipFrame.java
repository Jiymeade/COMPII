import javax.swing.*;
import java.awt.*;

public class BattleshipFrame extends JFrame {
    private GameBoard board;
    private GameStats stats;
    private JButton[][] buttons;
    private JLabel missLabel, strikeLabel, totalMissLabel, totalHitLabel;

    public BattleshipFrame() {
        super("Battleship");

        board = new GameBoard(10, 10);
        stats = new GameStats();

        setLayout(new BorderLayout());

        // Grid panel
        JPanel gridPanel = new JPanel(new GridLayout(10, 10));
        buttons = new JButton[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                JButton button = new JButton("~");
                final int row = i;
                final int col = j;
                button.addActionListener(e -> handleClick(row, col, button));
                buttons[i][j] = button;
                gridPanel.add(button);
            }
        }

        // Stats panel
        JPanel statsPanel = new JPanel(new GridLayout(2, 2));
        missLabel = new JLabel("Miss: 0");
        strikeLabel = new JLabel("Strikes: 0");
        totalMissLabel = new JLabel("Total Misses: 0");
        totalHitLabel = new JLabel("Total Hits: 0");
        statsPanel.add(missLabel);
        statsPanel.add(strikeLabel);
        statsPanel.add(totalMissLabel);
        statsPanel.add(totalHitLabel);

        // Control panel
        JPanel controlPanel = new JPanel();
        JButton playAgainBtn = new JButton("Play Again");
        JButton quitBtn = new JButton("Quit");

        playAgainBtn.addActionListener(e -> resetGame());
        quitBtn.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(this, "Quit the game?", "Quit", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) System.exit(0);
        });

        controlPanel.add(playAgainBtn);
        controlPanel.add(quitBtn);

        add(gridPanel, BorderLayout.CENTER);
        add(statsPanel, BorderLayout.NORTH);
        add(controlPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setSize(600, 700);
        setVisible(true);

        board.placeShipsRandomly();
    }

    private void handleClick(int i, int j, JButton button) {
        if (!button.isEnabled()) return;
        button.setEnabled(false);
        Cell cell = board.getCell(i, j);


        boolean hit = board.fireAt(i, j);
        if (hit) {
            button.setText("X");
            button.setBackground(Color.RED);
            stats.registerHit();
            Ship ship = cell.getShip();

            if(ship.isSunk() && !ship.isSunkNotified()){
                ship.setSunkNotified(true);
                JOptionPane.showMessageDialog(this, "You sunk a ship!");

            }
            if (board.allShipsSunk()) {
                JOptionPane.showMessageDialog(this, "You Win! All ships sunk!");
                int choice = JOptionPane.showConfirmDialog(this, "Play Again?");
                if (choice == JOptionPane.YES_OPTION) {
                    resetGame();
                } else {
                    System.exit(0);
                }
            }
        stats.resetMissCounterOnly();
        } else {
            button.setText("M");
            button.setBackground(Color.YELLOW);
            stats.registerMiss();

            if (stats.getMissCount() >= 5) {
                stats.registerStrike();
                stats.resetMissCounterOnly();
            }

            if (stats.getStrikeCount() >= 3) {
                JOptionPane.showMessageDialog(this, "You lose! Too many strikes!");
                int choice = JOptionPane.showConfirmDialog(this, "Play Again?");
                if (choice == JOptionPane.YES_OPTION) {
                    resetGame();
                } else {
                    System.exit(0);
                }
            }
        }

        updateStats();
    }

    private void resetGame() {
        stats.reset();
        board.placeShipsRandomly();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                buttons[i][j].setText("~");
                buttons[i][j].setEnabled(true);
                buttons[i][j].setBackground(null);
            }
        }

        updateStats();
    }

    private void updateStats() {
        missLabel.setText("Miss: " + stats.getMissCount());
        strikeLabel.setText("Strikes: " + stats.getStrikeCount());
        totalHitLabel.setText("Total Hits: " + stats.getTotalHitCount());
        totalMissLabel.setText("Total Misses: " + stats.getTotalMissCount());
    }
}


