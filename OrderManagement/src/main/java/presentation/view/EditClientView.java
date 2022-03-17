package presentation.view;

import model.Client;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * @author Technical University of Cluj-Napoca, Petrache Remus-Mircea
 * @since Apr 19, 2021
 */
public class EditClientView extends JFrame {

    private final JButton submitButton;
    private final JTextField nameField;
    private final JTextField emailField;
    private final JTextField addressField;

    /**
     * View for editing a client's information
     * @param c client to be edited
     */
    public EditClientView(Client c) {
        int id = c.getIdClient();
        String name = c.getName();
        String email = c.getEmailAddress();
        String address = c.getAddress();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 250, 250);
        setTitle("Edit Client");
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setBackground(new Color(62, 137, 153));
        contentPane.setLayout(null);

        JLabel titleLabel = new JLabel("Edit client with id " + id);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        titleLabel.setBounds(45, 20, 150, 20);
        contentPane.add(titleLabel);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 65, 45, 13);
        contentPane.add(nameLabel);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(20, 100, 45, 13);
        contentPane.add(emailLabel);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(20, 135, 55, 13);
        contentPane.add(addressLabel);

        nameField = new JTextField(name);
        nameField.setBounds(60, 63, 165, 20);
        contentPane.add(nameField);
        nameField.setColumns(10);

        emailField = new JTextField(email);
        emailField.setColumns(10);
        emailField.setBounds(57, 98, 168, 20);
        contentPane.add(emailField);

        addressField = new JTextField(address);
        addressField.setColumns(10);
        addressField.setBounds(73, 133, 152, 20);
        contentPane.add(addressField);

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
     * Gets the new client's name
     * @return client's name
     */
    public String getClientName() {
        return nameField.getText();
    }

    /**
     * Gets the new client's email
     * @return client's email
     */
    public String getEmail() {
        return emailField.getText();
    }

    /**
     * Gets the new client's address
     * @return client's address
     */
    public String getAddress() {
        return addressField.getText();
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
