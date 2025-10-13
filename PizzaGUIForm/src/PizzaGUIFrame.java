import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class PizzaGUIFrame extends JFrame {

    private JPanel mainPanel;
    private JPanel crustPanel;
    private JPanel sizePanel;
    private JPanel toppingsPanel;
    private JPanel displayPanel;
    private JPanel buttonPanel;

    private JRadioButton thinCrust, regularCrust, deepDishCrust;
    private JComboBox<String> sizeCombo;
    private JCheckBox[] toppingBox;
    private JTextArea receiptInfo;
    private JButton orderButton, clearButton, quitButton;
    private ButtonGroup crustGroup;

    private final String[] sizes = {"Small", "Medium", "Large", "Super"};
    private final Double[] sizePrice = {8.0, 12.0, 16.0, 20.0};
    private final String[] toppings = {
            "Pinapples", "Pepperoni", "Sausage", "Bell Peppers", "Bacon", "Ham"
    };

    public PizzaGUIFrame() {
        super("PizzaGUI Order Form");
        createMainPanel();
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    private void createMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        add(mainPanel);
        createCrustPanel();
        createSizePanel();
        createToppingsPanel();
        createDisplayPanel();
        createButtonPanel();
    }

    private void createCrustPanel() {
        crustPanel = new JPanel(new GridLayout(3, 1));
        crustPanel.setBorder(BorderFactory.createTitledBorder("Select a Crust"));
        thinCrust = new JRadioButton("Thin");
        regularCrust = new JRadioButton("Regular");
        deepDishCrust = new JRadioButton("Deep Dish");

        crustGroup = new ButtonGroup();
        crustGroup.add(thinCrust);
        crustGroup.add(deepDishCrust);
        crustGroup.add(regularCrust);

        crustPanel.add(thinCrust);
        crustPanel.add(regularCrust);
        crustPanel.add(deepDishCrust);
        mainPanel.add(crustPanel, BorderLayout.WEST);
    }

    private void createSizePanel() {
        sizePanel = new JPanel();
        sizePanel.setBorder(BorderFactory.createTitledBorder("Select a Size"));
        sizeCombo = new JComboBox<>(sizes);
        sizePanel.add(sizeCombo);
        mainPanel.add(sizePanel, BorderLayout.NORTH);
    }

    private void createToppingsPanel() {
        toppingsPanel = new JPanel(new GridLayout(3, 2));
        toppingsPanel.setBorder(BorderFactory.createTitledBorder("Select a Topping"));
        toppingBox = new JCheckBox[toppings.length];
        for (int i = 0; i < toppings.length; i++) {
            toppingBox[i] = new JCheckBox(toppings[i]);
            toppingsPanel.add(toppingBox[i]);
        }
        mainPanel.add(toppingsPanel, BorderLayout.EAST);
    }

    private void createDisplayPanel() {
        displayPanel = new JPanel();
        receiptInfo = new JTextArea(20, 60);
        receiptInfo.setFont(new Font("MonoSpaced", Font.PLAIN, 14));
        receiptInfo.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(receiptInfo);
        displayPanel.add(scrollPane);
        mainPanel.add(displayPanel, BorderLayout.CENTER);
    }

    private void createButtonPanel() {
        buttonPanel = new JPanel(new GridLayout(1, 3));
        orderButton = new JButton("Order");
        clearButton = new JButton("Clear");
        quitButton = new JButton("Quit");

        Font buttonFont = new Font("SansSerif", Font.BOLD, 14);
        orderButton.setFont(buttonFont);
        clearButton.setFont(buttonFont);
        quitButton.setFont(buttonFont);

        buttonPanel.add(orderButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(quitButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        orderButton.addActionListener(e -> {
            String crust = null;
            if (thinCrust.isSelected()) {
                crust = "Thin";
            } else if (regularCrust.isSelected()) {
                crust = "Regular";
            } else if (deepDishCrust.isSelected()) {
                crust = "Deep Dish";
            }

            if (crust == null) {
                JOptionPane.showMessageDialog(null, "Please select a Crust");
                return;
            }

            int sizeIndex = sizeCombo.getSelectedIndex();
            String size = sizes[sizeIndex];
            double base = sizePrice[sizeIndex];

            ArrayList<String> selectedToppings = new ArrayList<>(toppings.length);
            for (JCheckBox box : toppingBox) {
                if (box.isSelected()) selectedToppings.add(box.getText());
            }

            if (selectedToppings.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please select a Topping");
                return;
            }

            double toppingCost = selectedToppings.size();
            double subtotal = base + toppingCost;
            double tax = subtotal * 0.07;
            double total = subtotal + tax;

            StringBuilder receipt = new StringBuilder();
            receipt.append("====================================================\n");
            receipt.append(String.format("Crust: %-20s\n", crust));
            receipt.append(String.format("Size: %-20s $%.2f\n ", size, base));
            receipt.append("Toppings:\n");
            for (String topping : selectedToppings) {
                receipt.append(String.format(" %-25s $1.00\n", topping));
            }
            receipt.append("\n");
            receipt.append(String.format("Subtotal:\t\t$%.2f\n", subtotal));
            receipt.append(String.format("Tax:\t\t$%.2f\n", tax));
            receipt.append("-------------------------------------------------------");
            receipt.append(String.format("Total:\t\t\t$%.2f\n", total));
            receipt.append("======================================================");
            receiptInfo.setText(receipt.toString());
        });


        clearButton.addActionListener(e -> {
            crustGroup.clearSelection();
            sizeCombo.setSelectedIndex(0);
            for (JCheckBox box : toppingBox) {
                box.setSelected(false);
            }
            receiptInfo.setText("");
        });

        quitButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you Sure?", "Confirm Quit", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }
}











