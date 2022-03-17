package presentation.controller;

import bll.OrderBLL;
import model.ClientOrder;
import presentation.view.AddOrderView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Technical University of Cluj-Napoca, Petrache Remus-Mircea
 * @since Apr 19, 2021
 */
public class AddOrderController implements ActionListener {
    private final OrderBLL orderBLL;
    private final ArrayList<Integer> clientIds;
    private final ArrayList<Integer> productIds;

    /**
     * Instantiates the orderBLL parameter and sets the client and product ids
     * @param clientIds possible client ids
     * @param productIds possible product ids
     */
    public AddOrderController(List<Integer> clientIds, List<Integer> productIds) {
        this.orderBLL = new OrderBLL();
        this.clientIds = (ArrayList<Integer>) clientIds;
        this.productIds = (ArrayList<Integer>) productIds;
    }

    /**
     * Adds a new order and displays success message if valid, otherwise displays an error message
     * @param e click on the add button
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        AddOrderView addOrderView = new AddOrderView(clientIds, productIds);
        addOrderView.addSubmitListener(e1 -> {
            try {
                int quantity = Integer.parseInt(addOrderView.getQuantity());
                if (quantity <= 0) {
                    throw new NumberFormatException();
                }
                int orderId = orderBLL.insertOrder(new ClientOrder(addOrderView.getClientId(), addOrderView.getProductId(), quantity));
                addOrderView.showSuccess("Order with id " + orderId + " has been successfully added!");
            } catch (NumberFormatException ex) {
                addOrderView.showError("Please enter a valid quantity!");
            } catch (Exception ex) {
                addOrderView.showError(ex.getMessage());
            }

        });
        addOrderView.setVisible(true);
    }
}
