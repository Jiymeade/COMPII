import javax.swing.*;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandProductSearch extends JFrame {
    private JTextField searchTxt;
    private JTextArea ResultArea;
    private JButton searchBtn;
    private RandomAccessFile raf;

    public RandProductSearch() {
        super("Random Product Search");
        setSize(500, 400);
        setLayout(null);
        JLabel lblSearch = new JLabel("Partial Name:");
        lblSearch.setBounds(10, 10, 100, 30);
        add(lblSearch);

        searchTxt = new JTextField();
        searchTxt.setBounds(120, 10, 200, 30);
        add(searchTxt);

        searchBtn = new JButton("Search");
        searchBtn.setBounds(330, 10, 100, 30);
        add(searchBtn);

        ResultArea = new JTextArea();
        ResultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(ResultArea);
        scrollPane.setBounds(10, 60, 460, 300);
        add(scrollPane);

        searchBtn.addActionListener(e -> searchProduct());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        try {
            raf = new RandomAccessFile("Product.dat", "r");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void searchProduct() {
        String searchTerm = searchTxt.getText().toLowerCase();
        ResultArea.setText("");
        if (searchTerm.isEmpty()) {
            ResultArea.setText("Please enter a search term");
            return;
        }


        try {
            raf.seek(0);
            long numRecords = raf.length() / Product.Record_Size;
            for (int i = 0; i < numRecords; i++) {
                Product p = Product.readRandomAccess(raf);
                if (p.getName().toLowerCase().contains(searchTerm)) {
                    ResultArea.append(p.toCSV() + "\n");
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
       SwingUtilities.invokeLater(()->new RandProductSearch());
    }
}