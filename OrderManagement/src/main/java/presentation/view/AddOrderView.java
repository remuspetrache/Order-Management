package presentation.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * @author Technical University of Cluj-Napoca, Petrache Remus-Mircea
 * @since Apr 19, 2021
 */
public class AddOrderView extends JFrame {
    private final JButton submitButton;
    private final JTextField quantityField;
    private final JComboBox<Integer> clientIdComboBox;
    private final JComboBox<Integer> productIdComboBox;

    /**
     * View for adding new order information
     * @param clientIds possible client id options
     * @param productIds possible product id options
     */
    public AddOrderView(ArrayList<Integer> clientIds, ArrayList<Integer> productIds) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 250, 250);
        setTitle("Add order");
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setBackground(new Color(62, 137, 153));
        contentPane.setLayout(null);

        JLabel titleLabel = new JLabel("Add a new order");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        titleLabel.setBounds(50, 20, 150, 20);
        contentPane.add(titleLabel);

        JLabel clientIdLabel = new JLabel("Client ID:");
        clientIdLabel.setBounds(20, 65, 60, 13);
        contentPane.add(clientIdLabel);

        JLabel productIdLabel = new JLabel("Product ID:");
        productIdLabel.setBounds(20, 100, 80, 13);
        contentPane.add(productIdLabel);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(20, 135, 55, 13);
        contentPane.add(quantityLabel);

        quantityField = new JTextField();
        quantityField.setColumns(10);
        quantityField.setBounds(75, 133, 35, 20);
        contentPane.add(quantityField);

        submitButton = new JButton("Submit");
        submitButton.setBounds(75, 175, 85, 21);
        contentPane.add(submitButton);

        clientIdComboBox = new JComboBox(clientIds.toArray());
        clientIdComboBox.setBounds(77, 61, 50, 20);
        contentPane.add(clientIdComboBox);

        productIdComboBox = new JComboBox(productIds.toArray());
        productIdComboBox.setBounds(87, 96, 50, 20);
        contentPane.add(productIdComboBox);
    }

    /**
     * Adds action listener for the submit button
     * @param sal action listener for the submit button
     */
    public void addSubmitListener(ActionListener sal) {
        submitButton.addActionListener(sal);
    }

    /**
     * Gets the new order's client id
     * @return order's client id
     */
    public int getClientId() {
        return (Integer) clientIdComboBox.getSelectedItem();
    }

    /**
     * Gets the new order's product id
     * @return order's product id
     */
    public int getProductId() {
        return (Integer) productIdComboBox.getSelectedItem();
    }

    /**
     * Gets the new order's quantity
     * @return new order's quantity
     */
    public String getQuantity() {
        return quantityField.getText();
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
