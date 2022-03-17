package presentation.controller;

import bll.ClientBLL;
import presentation.view.ClientView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Technical University of Cluj-Napoca, Petrache Remus-Mircea
 * @since Apr 19, 2021
 */
public class DeleteClientController implements ActionListener {
    private final ClientBLL clientBLL;
    private final ClientView clientView;

    /**
     * Instantiates the clientBLL parameter and sets the clientView
     * @param clientView view where to display messages
     */
    public DeleteClientController(ClientView clientView) {
        this.clientView = clientView;
        this.clientBLL = new ClientBLL();
    }

    /**
     * Deletes client and displays success message if found, otherwise displays error message
     * @param e click on the delete button
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int idClient = Integer.parseInt(clientView.getDeleteClientId());
            clientBLL.deleteClient(idClient);
            clientView.showSuccess("Client with id " + idClient + " has been successfully deleted!");
        } catch (NumberFormatException ex) {
            clientView.showError("Invalid client id!");
        } catch (Exception ex) {
            clientView.showError(ex.getMessage());
        }
    }
}

