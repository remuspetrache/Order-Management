package presentation.controller;

import presentation.view.*;

/**
 * @author Technical University of Cluj-Napoca, Petrache Remus-Mircea
 * @since Apr 19, 2021
 */
public class Controller {

    /**
     * Adds the action listeners to the main view's buttons
     * @param mainView main view of the application
     */
    public Controller(MainView mainView) {
        mainView.addClientListener(new ShowClientController());
        mainView.addProductListener(new ShowProductController());
        mainView.addOrderListener(new ShowOrderController());
    }
}
