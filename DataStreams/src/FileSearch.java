import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileSearch  extends JFrame {

    private JTextArea originalTextArea;
    private JTextArea filteredTextArea;
    private JTextField searchTextField;
    private JButton searchButton;
    private JButton loadButton;
    private JButton quitButton;
    private File loadedFile;

    public FileSearch() {
        super("File Search");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLayout(new BorderLayout(10, 10));
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        searchTextField = new JTextField(20);
        loadButton = new JButton("Load File");
        searchButton = new JButton("Search");
        quitButton = new JButton("Quit");

        topPanel.add(new JLabel("Select String:"));
        topPanel.add(searchTextField);
        topPanel.add(loadButton);
        topPanel.add(searchButton);
        topPanel.add(quitButton);

        originalTextArea = new JTextArea();
        filteredTextArea = new JTextArea();
        originalTextArea.setEditable(false);
        filteredTextArea.setEditable(false);

        JScrollPane leftScroll = new JScrollPane(originalTextArea);
        JScrollPane rightScroll = new JScrollPane(filteredTextArea);
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftScroll, rightScroll);
        splitPane.setDividerLocation(450);
        add(splitPane, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        loadButton.addActionListener(e -> loadFile());
        searchButton.addActionListener(e -> searchFile());
        quitButton.addActionListener(e -> System.exit(0));
        setVisible(true);
    }

    private void loadFile() {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            loadedFile = chooser.getSelectedFile();
        }
        try {
            List<String> allLines;
            try (Stream<String> lines = Files.lines(loadedFile.toPath())) {
                allLines = lines.collect(Collectors.toList());
            }
            originalTextArea.setText(String.join("\n", allLines));
            filteredTextArea.setText("");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading file:" + e.getMessage());
        }
    }

    private void searchFile() {
        if (loadedFile == null) {
            JOptionPane.showMessageDialog(this, "Please enter a search word ");
            return;
        }
        String searchTerm = searchTextField.getText().trim();
        if (searchTerm.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a search term");
            return;
        }
        try (Stream<String> lines = Files.lines(loadedFile.toPath())) {
            List<String> filtered = lines
                    .filter(line -> line.toLowerCase().contains(searchTerm.toLowerCase()))
                    .collect(Collectors.toList());
            filteredTextArea.setText(String.join("\n", filtered));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error Searching file");
        }
    }
}
