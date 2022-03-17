package presentation.controller;

import bll.ClientBLL;
import model.Client;
import presentation.view.ClientView;
import presentation.view.EditClientView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Technical University of Cluj-Napoca, Petrache Remus-Mircea
 * @since Apr 19, 2021
 */
public class EditClientController implements ActionListener {
    private final ClientBLL clientBLL;
    private final ClientView clientView;

    /**
     * Instantiates the clientBLL parameter and sets clientView
     * @param clientView view where to display messages
     */
    public EditClientController(ClientView clientView) {
        this.clientView = clientView;
        this.clientBLL = new ClientBLL();
    }

    /**
     * Edits client and displays success message if found, otherwise displays error message
     * @param e click on the edit button
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int idClient = Integer.parseInt(clientView.getEditClientId());
            Client c = clientBLL.findClientById(idClient);
            EditClientView editClientView = new EditClientView(c);
            editClientView.addSubmitListener(e1 -> {
                try {
                    if (editClientView.getClientName().equals("") || editClientView.getAddress().equals("") || editClientView.getEmail().equals("")) {
                        throw new IllegalArgumentException("Please do not leave any fields empty!");
                    }
                    clientBLL.updateClient(idClient, new Client(editClientView.getClientName(), editClientView.getEmail(), editClientView.getAddress()));
                    editClientView.showSuccess("Client with id " + idClient + " has been successfully edited!");
                } catch (Exception ex) {
                    editClientView.showError(ex.getMessage());
                }
            });
            editClientView.setVisible(true);
        } catch (NumberFormatException ex) {
            clientView.showError("Invalid client id!");
        } catch (Exception ex) {
            clientView.showError(ex.getMessage());
        }
    }
}

