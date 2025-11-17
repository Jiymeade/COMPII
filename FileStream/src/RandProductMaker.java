import javax.swing.*;
import java.io.IOException;
import java.io.RandomAccessFile;


    public class  RandProductMaker extends JFrame {
        private JTextField txtID, txtName, txtDescription, txtCost, txtCount;
        private JButton buttonAdd;
        private int count = 0;
        private RandomAccessFile raf;

        public RandProductMaker() {
            super("Random Product Maker");
            setSize(400, 300);
            setLayout(null);

            JLabel lblID = new JLabel("ID");
            lblID.setBounds(20, 20, 80, 25);
            add(lblID);
            txtID = new JTextField();
            txtID.setBounds(100, 20, 150, 25);
            add(txtID);

            JLabel lblName = new JLabel("Name");
            lblName.setBounds(20, 60, 80, 25);
            add(lblName);
            txtName = new JTextField();
            txtName.setBounds(100, 60, 150, 25);
            add(txtName);

            JLabel lblDescription = new JLabel("Description");
            lblDescription.setBounds(20, 90, 80, 25);
            add(lblDescription);
            txtDescription = new JTextField();
            txtDescription.setBounds(100, 90, 150, 25);
            add(txtDescription);

            JLabel lblCost = new JLabel("Cost");
            lblCost.setBounds(20, 130, 80, 25);
            add(lblCost);
            txtCost = new JTextField();
            txtCost.setBounds(100, 130, 150, 25);
            add(txtCost);

            JLabel lblCount = new JLabel("Record Count:");
            lblCount.setBounds(20, 170, 80, 25);
            add(lblCount);
            txtCount = new JTextField();
            txtCount.setBounds(100, 170, 150, 25);
            txtCount.setEditable(false);
            add(txtCount);

            buttonAdd = new JButton("Add");
            buttonAdd.setBounds(20, 190, 80, 25);
            add(buttonAdd);
            buttonAdd.addActionListener(e -> addProduct());
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
            try {
                raf = new RandomAccessFile("Product.dat", "rw");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        private void addProduct() {
            try {
                String id = txtID.getText();
                String name = txtName.getText();
                String description = txtDescription.getText();
                double cost = Double.parseDouble(txtCost.getText());

                if (id.isEmpty() || name.isEmpty() || description.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all the fields");
                    return;
                }

                Product p = new Product(id, name, description, cost);
                raf.seek(raf.length());
                p.writeRandomAccess(raf);
                count++;
                txtCount.setText(String.valueOf(count));
                txtID.setText("");
                txtName.setText("");
                txtDescription.setText("");
                txtCost.setText("");
                txtID.requestFocus();
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(this, "Invalid Cost Value!");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        public static void main(String[] args) {
            SwingUtilities.invokeLater(()->new RandProductMaker());
        }
    }

