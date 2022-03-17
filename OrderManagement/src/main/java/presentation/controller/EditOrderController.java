package presentation.controller;

import bll.OrderBLL;
import model.ClientOrder;
import presentation.view.EditOrderView;
import presentation.view.OrderView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Technical University of Cluj-Napoca, Petrache Remus-Mircea
 * @since Apr 19, 2021
 */
public class EditOrderController implements ActionListener {
    private final OrderView orderView;
    private final OrderBLL orderBLL;
    private final ArrayList<Integer> clientIds;
    private final ArrayList<Integer> productIds;

    /**
     * Instantiates the orderBLL parameter, sets the orderView, the client ids and the product ids
     * @param orderView view where to display messages
     * @param clientIds possible client ids
     * @param productIds possible product ids
     */
    public EditOrderController(OrderView orderView, List<Integer> clientIds, List<Integer> productIds) {
        this.orderView = orderView;
        this.orderBLL = new OrderBLL();
        this.clientIds= (ArrayList<Integer>) clientIds;
        this.productIds = (ArrayList<Integer>) productIds;
    }

    /**
     * Edits order and displays success message if found, otherwise displays error message
     * @param e click on the edit button
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int idOrder = Integer.parseInt(orderView.getEditOrderId());
            ClientOrder o = orderBLL.findOrderById(idOrder);
            EditOrderView editOrderView = new EditOrderView(clientIds, productIds, o);
            editOrderView.addSubmitListener(e1 -> {
                try {
                    int quantity = Integer.parseInt(editOrderView.getQuantity());
                    if (quantity <= 0) {
                        throw new NumberFormatException();
                    }
                    orderBLL.updateOrder(idOrder, new ClientOrder(editOrderView.getClientId(), editOrderView.getProductId(), quantity));
                    editOrderView.showSuccess("Order with id " + idOrder + " has been successfully edited!");
                } catch (NumberFormatException ex) {
                    editOrderView.showError("Please enter a valid quantity!");
                } catch (Exception ex) {
                    editOrderView.showError(ex.getMessage());
                }
            });
            editOrderView.setVisible(true);
        } catch (NumberFormatException ex) {
            orderView.showError("Invalid order id!");
        } catch (Exception ex) {
            orderView.showError(ex.getMessage());
        }
    }
}
