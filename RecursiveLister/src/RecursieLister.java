import javax.swing.*;
import java.awt.*;
import java.io.File;

public class RecursieLister extends JFrame {
    private JTextArea displayArea;
    private JButton startButton, quitButton;

    public RecursieLister() {
        super("RecursiveLister");
        setSize(600, 400);
        setLayout(new BorderLayout(10,10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("Recursive Directory Lister", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        startButton = new JButton("Start");
        buttonPanel.add(startButton);
        quitButton = new JButton("Quit");
        buttonPanel.add(quitButton);
        add(buttonPanel, BorderLayout.SOUTH);
        startButton.addActionListener(e -> selectDirectory());
        quitButton.addActionListener(e -> System.exit(0));
        setVisible(true);
    }

    private void selectDirectory() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            displayArea.setText("Directory Selected:\n" + selectedFile.getAbsolutePath());
            listFiles(selectedFile);
        }
    }

    private void listFiles(File file) {
        File[] files = file.listFiles();
        if (files == null) return;
        for (File f : files) {
            displayArea.append(f.getAbsolutePath() + "\n");
            if (f.isDirectory()) {
                listFiles(f);
            }
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> new  RecursieLister());
    }

}
