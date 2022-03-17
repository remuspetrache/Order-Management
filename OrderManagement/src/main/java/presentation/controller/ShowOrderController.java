package presentation.controller;

import bll.ClientBLL;
import bll.ProductBLL;
import model.Client;
import model.Product;
import presentation.view.OrderView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @author Technical University of Cluj-Napoca, Petrache Remus-Mircea
 * @since Apr 19, 2021
 */
public class ShowOrderController implements ActionListener {
    private final ClientBLL clientBLL;
    private final ProductBLL productBLL;

    /**
     * Instantiates the clientBLL and productBLL parameters
     */
    public ShowOrderController() {
        clientBLL = new ClientBLL();
        productBLL = new ProductBLL();
    }

    /**
     * Generates the possible client ids
     * @return possible client ids
     */
    private ArrayList<Integer> generateClientIds() {
        ArrayList<Integer> clientIds = new ArrayList<>();
        for (Client c : clientBLL.getAllClients()) {
            clientIds.add(c.getIdClient());
        }
        return clientIds;
    }

    /**
     * Generates the possible product ids
     * @return possible product ids
     */
    private ArrayList<Integer> generateProductIds() {
        ArrayList<Integer> productIds = new ArrayList<>();
        for (Product p : productBLL.getAllProducts()) {
            productIds.add(p.getIdProduct());
        }
        return productIds;
    }

    /**
     * Displays the order view and adds its action listeners
     * @param e click on orders button
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        OrderView orderView = new OrderView();
        orderView.addAddListener(new AddOrderController(generateClientIds(), generateProductIds()));
        orderView.addEditListener(new EditOrderController(orderView, generateClientIds(), generateProductIds()));
        orderView.addDeleteListener(new DeleteOrderController(orderView));
        orderView.addViewListener(new ShowTableController("Order"));
        orderView.setVisible(true);
    }
}
