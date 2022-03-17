package start;

import presentation.controller.Controller;
import presentation.view.MainView;

/**
 * @author Technical University of Cluj-Napoca, Petrache Remus-Mircea
 * @since Apr 19, 2021
 */
public class Start {

    /**
     * Starts the application.
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        MainView mainView = new MainView();
        Controller controller = new Controller(mainView);
        mainView.setVisible(true);
    }
}
