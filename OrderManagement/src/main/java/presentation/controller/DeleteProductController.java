package presentation.controller;

import bll.ProductBLL;
import presentation.view.ProductView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Technical University of Cluj-Napoca, Petrache Remus-Mircea
 * @since Apr 19, 2021
 */
public class DeleteProductController implements ActionListener {
    private final ProductView productView;
    private final ProductBLL productBLL;

    /**
     * Instantiates productBLL parameter and sets productView
     * @param productView view where to display messages
     */
    public DeleteProductController(ProductView productView) {
        this.productView = productView;
        this.productBLL = new ProductBLL();
    }

    /**
     * Deletes product and displays success message if found, otherwise displays error message
     * @param e click on the delete button
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int idProduct = Integer.parseInt(productView.getDeleteOrderId());
            productBLL.deleteProduct(idProduct);
            productView.showSuccess("Product with id " + idProduct + " has been successfully deleted!");
        } catch (NumberFormatException ex) {
            productView.showError("Invalid product id!");
        } catch (Exception ex) {
            productView.showError(ex.getMessage());
        }
    }
}
