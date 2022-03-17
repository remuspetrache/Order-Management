package presentation.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @author Technical University of Cluj-Napoca, Petrache Remus-Mircea
 * @since Apr 19, 2021
 */
public class ClientView extends JFrame {

    private final JButton addButton;
    private final JButton editButton;
    private final JButton deleteButton;
    private final JButton viewButton;
    private final JTextField editField;
    private final JTextField deleteField;

    /**
     * View for the client operations
     */
    public ClientView() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Client");
        setBounds(100, 100, 330, 370);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setBackground(new Color(62, 137, 153));
        contentPane.setLayout(null);

        JLabel titleLabel = new JLabel("Client Operations");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        titleLabel.setBounds(60, 20, 200, 25);
        contentPane.add(titleLabel);

        JLabel addLabel = new JLabel("Add a new client");
        addLabel.setHorizontalAlignment(SwingConstants.CENTER);
        addLabel.setBounds(90, 65, 120, 15);
        contentPane.add(addLabel);

        addButton = new JButton("Add");
        addButton.setBounds(110, 90, 80, 20);
        contentPane.add(addButton);

        JLabel editLabel = new JLabel("Edit client with id:");
        editLabel.setHorizontalAlignment(SwingConstants.CENTER);
        editLabel.setBounds(90, 130, 120, 15);
        contentPane.add(editLabel);

        editField = new JTextField();
        editField.setBounds(205, 129, 35, 20);
        contentPane.add(editField);
        editField.setColumns(10);

        editButton = new JButton("Edit");
        editButton.setBounds(110, 155, 80, 20);
        contentPane.add(editButton);

        JLabel deleteLabel = new JLabel("Delete client with id:");
        deleteLabel.setHorizontalAlignment(SwingConstants.CENTER);
        deleteLabel.setBounds(90, 195, 120, 15);
        contentPane.add(deleteLabel);

        deleteField = new JTextField();
        deleteField.setColumns(10);
        deleteField.setBounds(212, 194, 35, 20);
        contentPane.add(deleteField);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(110, 220, 80, 20);
        contentPane.add(deleteButton);

        JLabel viewLabel = new JLabel("View all clients");
        viewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        viewLabel.setBounds(90, 260, 120, 15);
        contentPane.add(viewLabel);

        viewButton = new JButton("View");
        viewButton.setBounds(110, 285, 80, 20);
        contentPane.add(viewButton);
    }

    /**
     * Adds action listener for the add client button
     * @param aal action listener of the add button
     */
    public void addAddListener(ActionListener aal) {
        addButton.addActionListener(aal);
    }

    /**
     * Adds action listener for the edit client button
     * @param eal action listener of the edit button
     */
    public void addEditListener(ActionListener eal) {
        editButton.addActionListener(eal);
    }

    /**
     * Adds action listener for the delete client button
     * @param dal action listener of the delete button
     */
    public void addDeleteListener(ActionListener dal) {
        deleteButton.addActionListener(dal);
    }

    /**
     * Adds action listener for the view clients button
     * @param val action listener of the view button
     */
    public void addViewListener(ActionListener val) {
        viewButton.addActionListener(val);
    }

    /**
     * Gets the id of the client to be edited
     * @return id of the client
     */
    public String getEditClientId() {
        return editField.getText();
    }

    /**
     * Gets the id of the client to be deleted
     * @return id of the client
     */
    public String getDeleteClientId() {
        return deleteField.getText();
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
