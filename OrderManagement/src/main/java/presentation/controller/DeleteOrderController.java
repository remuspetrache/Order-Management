package presentation.controller;

import bll.OrderBLL;
import presentation.view.OrderView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Technical University of Cluj-Napoca, Petrache Remus-Mircea
 * @since Apr 19, 2021
 */
public class DeleteOrderController implements ActionListener {
    private final OrderBLL orderBLL;
    private final OrderView orderView;

    /**
     * Instantiates the orderBLL parameter and sets the orderView
     * @param orderView view where to display messages
     */
    public DeleteOrderController(OrderView orderView) {
        this.orderBLL = new OrderBLL();
        this.orderView = orderView;
    }

    /**
     * Deletes order and displays success message if found, otherwise displays error message
     * @param e click on the delete button
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int idOrder = Integer.parseInt(orderView.getDeleteOrderId());
            orderBLL.deleteOrder(idOrder);
            orderView.showSuccess("Order with id " + idOrder + " has been successfully deleted!");
        } catch (NumberFormatException ex) {
            orderView.showError("Invalid order id!");
        } catch (Exception ex) {
            orderView.showError(ex.getMessage());
        }
    }
}
