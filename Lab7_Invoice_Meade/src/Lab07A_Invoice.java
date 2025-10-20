import javax.swing.*;
import java.awt.*;

public class Lab07A_Invoice extends JFrame {
  private JTextField customerName;
  private JTextField Address;
  private JTextField ProductName;
  private JTextField Quantity;
  private JTextField ProductPrice;

  private JTextArea outputArea;
  private JButton additemButton;
  private JButton showInvoiceButton;
  private JButton clearButton;
  private Invoice invoice;

  public Lab07A_Invoice() {
      super("Invoice");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(700, 500);
      setLocationRelativeTo(null);
      setLayout(new BorderLayout(10, 10));

      createInputPanel();
      createButtonPanel();
      createOutputPanel();
      setVisible(true);
  }

  private void createInputPanel() {
      JPanel inputPanel = new JPanel(new GridLayout(0,2,5,5));
      inputPanel.setBorder(BorderFactory.createTitledBorder("Customer and Product Info"));
      customerName = new JTextField();
      Address = new JTextField();
      ProductName = new JTextField();
      Quantity = new JTextField();
      ProductPrice = new JTextField();

      inputPanel.add(new JLabel("Customer Name:"));
      inputPanel.add(customerName);
      inputPanel.add(new JLabel("Address:"));
      inputPanel.add(Address);
      inputPanel.add(new JLabel("Product Name:"));
      inputPanel.add(ProductName);
      inputPanel.add(new JLabel("Quantity:"));
      inputPanel.add(Quantity);
      inputPanel.add(new JLabel("Product Price:"));
      inputPanel.add(ProductPrice);
      add(inputPanel, BorderLayout.NORTH);
  }
  private void createButtonPanel() {
      JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
      additemButton = new JButton("Add Item");
      showInvoiceButton = new JButton("Show Invoice");
      clearButton = new JButton("Clear");
      Font btnFront = new Font("SansSerif", Font.BOLD, 14);
      additemButton.setFont(btnFront);
      showInvoiceButton.setFont(btnFront);
      clearButton.setFont(btnFront);

      buttonPanel.add(additemButton);
      buttonPanel.add(showInvoiceButton);
      buttonPanel.add(clearButton);
      add(buttonPanel, BorderLayout.CENTER);

      additemButton.addActionListener(e -> additem());
      showInvoiceButton.addActionListener(e -> showInvoice());
      clearButton.addActionListener(e-> clearAll());
    }

    private void createOutputPanel() {
      JPanel outputPanel = new JPanel();
      outputPanel.setBorder(BorderFactory.createTitledBorder("Invoice Output"));
      outputArea = new JTextArea(20,70);
      outputArea.setEditable(false);
      outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

      JScrollPane scrollPane = new JScrollPane(outputArea);
      outputPanel.add(scrollPane);

      add(outputPanel, BorderLayout.SOUTH);
}
private void additem() {
    try {
        if (invoice == null) {
            String cusName = customerName.getText();
            String address = Address.getText();
            if (cusName.isEmpty() || address.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all the fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            invoice = new Invoice(new Customers(cusName, address));
        }
        String productName = ProductName.getText();
        if (productName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter valid Product Name", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        double price = Double.parseDouble(ProductPrice.getText());
        int quantity = Integer.parseInt(Quantity.getText());

        if (price < 0 || quantity <= 0) {
            JOptionPane.showMessageDialog(null, "Please enter a valid product price", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Product product = new Product(productName, price);
        LineItem lineItem = new LineItem(product, quantity);
        invoice.addLineItem(lineItem);

        outputArea.append(String.format(
                "Added: %-20s x%-2d @ $%-8.2f = $%.2f\n",
                product.getName(), quantity, product.getUnitPrice(), lineItem.getTotal()
        ));

        ProductName.setText("");
        ProductPrice.setText("");
        Quantity.setText("");
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(null, "Invalid number for price", "Error", JOptionPane.ERROR_MESSAGE);
    }
  }
  private void showInvoice() {
     if (invoice != null){
         outputArea.append(invoice.toString());
     }else{
         JOptionPane.showMessageDialog(null, "No invoice to show", "Error", JOptionPane.ERROR_MESSAGE);
     }
  }

  private void clearAll() {
      customerName.setText("");
      Address.setText("");
      ProductName.setText("");
      ProductPrice.setText("");
      Quantity.setText("");
      outputArea.setText("");
      invoice = null;

  }
}
