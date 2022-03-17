package presentation.controller;

import bll.ClientBLL;
import model.Client;
import presentation.view.AddClientView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Technical University of Cluj-Napoca, Petrache Remus-Mircea
 * @since Apr 19, 2021
 */
public class AddClientController implements ActionListener {
    private final ClientBLL clientBLL;

    /**
     * Instantiates the clientBLL parameter
     */
    public AddClientController() {
        clientBLL = new ClientBLL();
    }

    /**
     * Adds a new client and displays success message if valid, otherwise displays an error message
     * @param e click on the add button
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        AddClientView addClientView = new AddClientView();
        addClientView.addSubmitListener(e1 -> {
            try {
                if (addClientView.getClientName().equals("") || addClientView.getAddress().equals("") || addClientView.getEmail().equals("")) {
                    throw new IllegalArgumentException("Please complete all the fields!");
                }
                int clientId = clientBLL.insertClient(new Client(addClientView.getClientName(), addClientView.getEmail(), addClientView.getAddress()));
                addClientView.showSuccess("Client with id " + clientId + " has been successfully added!");
            } catch (Exception ex) {
                addClientView.showError(ex.getMessage());
            }
        });
        addClientView.setVisible(true);
    }
}

