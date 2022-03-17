package presentation.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @author Technical University of Cluj-Napoca, Petrache Remus-Mircea
 * @since Apr 19, 2021
 */
public class MainView extends JFrame {

    private final JButton clientsButton;
    private final JButton productsButton;
    private final JButton ordersButton;

    /**
     * Main view of the application
     */
    public MainView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Order Management");
        setBounds(100, 100, 300, 120);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setBackground(new Color(62, 137, 153));
        contentPane.setLayout(null);

        clientsButton = new JButton("Clients");
        clientsButton.setBounds(5, 45, 85, 20);
        clientsButton.setBackground(new Color(62, 153, 97));
        contentPane.add(clientsButton);

        productsButton = new JButton("Products");
        productsButton.setBounds(100, 45, 85, 20);
        productsButton.setBackground(new Color(62, 153, 97));
        contentPane.add(productsButton);

        ordersButton = new JButton("Orders");
        ordersButton.setBounds(195, 45, 85, 20);
        ordersButton.setBackground(new Color(62, 153, 97));
        contentPane.add(ordersButton);

        JLabel titleLabel = new JLabel("Order Management");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(70, 10, 150, 20);
        contentPane.add(titleLabel);
    }

    /**
     * Adds action listener for the clients button
     * @param cal action listener of the clients button
     */
    public void addClientListener(ActionListener cal) {
        clientsButton.addActionListener(cal);
    }

    /**
     * Adds action listener for the products button
     * @param pal action listener of the products button
     */
    public void addProductListener(ActionListener pal) {
        productsButton.addActionListener(pal);
    }

    /**
     * Adds action listener for the orders button
     * @param oal action listener of the orders button
     */
    public void addOrderListener(ActionListener oal) {
        ordersButton.addActionListener(oal);
    }
}
