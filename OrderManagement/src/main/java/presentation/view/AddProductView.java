package presentation.view;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * @author Technical University of Cluj-Napoca, Petrache Remus-Mircea
 * @since Apr 19, 2021
 */
public class AddProductView extends JFrame {
    private final JTextField nameField;
    private final JTextField quantityField;
    private final JTextField priceField;
    private final JButton submitButton;

    /**
     * View for adding new product information
     */
    public AddProductView() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 250, 250);
        setTitle("Add product");
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(new Color(62, 137, 153));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel titleLabel = new JLabel("Add a new product");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        titleLabel.setBounds(50, 20, 150, 20);
        contentPane.add(titleLabel);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 65, 45, 13);
        contentPane.add(nameLabel);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(20, 100, 60, 13);
        contentPane.add(quantityLabel);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(20, 135, 55, 13);
        contentPane.add(priceLabel);

        nameField = new JTextField();
        nameField.setBounds(60, 63, 165, 20);
        contentPane.add(nameField);
        nameField.setColumns(10);

        quantityField = new JTextField();
        quantityField.setColumns(10);
        quantityField.setBounds(75, 98, 35, 20);
        contentPane.add(quantityField);

        priceField = new JTextField();
        priceField.setColumns(10);
        priceField.setBounds(57, 133, 35, 20);
        contentPane.add(priceField);

        submitButton = new JButton("Submit");
        submitButton.setBounds(75, 175, 85, 21);
        contentPane.add(submitButton);
    }

    /**
     * Adds action listener for the submit button
     * @param sal action listener of the button
     */
    public void addSubmitListener(ActionListener sal) {
        submitButton.addActionListener(sal);
    }

    /**
     * Gets the new product's name
     * @return product's name
     */
    public String getProductName() {
        return nameField.getText();
    }

    /**
     * Gets the new product's quantity
     * @return product's quantity
     */
    public String getQuantity() {
        return quantityField.getText();
    }

    /**
     * Gets the new product's price
     * @return product's price
     */
    public String getPrice() {
        return priceField.getText();
    }

    /**
     * Displays an error message dialog
     * @param errorMessage message to be displayed
     */
    public void showError(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Input Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Displays a success message dialog
     * @param successMessage message to be displayed
     */
    public void showSuccess(String successMessage) {
        JOptionPane.showMessageDialog(this, successMessage, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}
