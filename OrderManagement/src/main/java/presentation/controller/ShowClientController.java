package presentation.controller;

import presentation.view.ClientView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Technical University of Cluj-Napoca, Petrache Remus-Mircea
 * @since Apr 19, 2021
 */
public class ShowClientController implements ActionListener {

    /**
     * Displays the client view and adds its action listeners
     * @param e click on the clients button
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ClientView clientView = new ClientView();
        clientView.addAddListener(new AddClientController());
        clientView.addEditListener(new EditClientController(clientView));
        clientView.addDeleteListener(new DeleteClientController(clientView));
        clientView.addViewListener(new ShowTableController("Client"));
        clientView.setVisible(true);
    }
}
