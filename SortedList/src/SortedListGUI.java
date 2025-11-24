import javax.swing.*;
import java.awt.*;

public class SortedListGUI extends JFrame {
    private SortedList sortedList = new SortedList();
    private JTextField addField, searchField;
    private JTextArea displayArea;

    public SortedListGUI() {
        super("SortedList");
        setSize(600, 400);
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        addField = new JTextField(12);
        searchField = new JTextField(12);

        JButton add = new JButton("Add");
        JButton search = new JButton("Search");

        panel.add(new JLabel("Add"));
        panel.add(addField);
        panel.add(add);
        panel.add(new JLabel("Search"));
        panel.add(searchField);
        panel.add(search);

        add(panel, BorderLayout.NORTH);



        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);
        add.addActionListener(e -> addItem());
        search.addActionListener(e -> searchItem());
        setVisible(true);
    }
    private void addItem() {
        String value = addField.getText();
        if (value.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a string to add");
            return;
        }
        sortedList.add(value);
        displayArea.append("Added:" + value + "\n");
        displayArea.append("List:" + sortedList + "\n\n");
        addField.setText("");
    }
    private void searchItem() {
        String value = searchField.getText();
        if (value.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a string to search");
            return;
        }
        int result = sortedList.binarySearch(value);
        if (result >= 0){
            displayArea.append("Search: " + value + ": Found at Index Number " + result + "\n");
        }else {
            displayArea.append("Search Failed:" + value + "\n");
            displayArea.append("Would be inserted at index:" + result + "\n");
        }

        displayArea.append("List:" + sortedList + "\n\n");
        searchField.setText("");
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->new SortedListGUI());
    }
}
