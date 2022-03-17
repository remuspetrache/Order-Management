package presentation.controller;

import bll.ProductBLL;
import model.Product;
import presentation.view.EditProductView;
import presentation.view.ProductView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Technical University of Cluj-Napoca, Petrache Remus-Mircea
 * @since Apr 19, 2021
 */
public class EditProductController implements ActionListener {
    private final ProductView productView;
    private final ProductBLL productBLL;

    /**
     * Instantiates productBLL parameter and sets productView
     * @param productView view where to display messages
     */
    public EditProductController(ProductView productView) {
        this.productView = productView;
        this.productBLL = new ProductBLL();
    }

    /**
     * Edits product and displays success message if found, otherwise displays error message
     * @param e click on the edit button
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int idProduct = Integer.parseInt(productView.getEditOrderId());
            Product p = productBLL.findProductById(idProduct);
            EditProductView editProductView = new EditProductView(p);
            editProductView.addSubmitListener(e1 -> {
                try {
                    int quantity = Integer.parseInt(editProductView.getQuantity());
                    int price = Integer.parseInt(editProductView.getPrice());
                    if (editProductView.getProductName().equals("") || quantity < 0 || price < 0) {
                        throw new NumberFormatException();
                    }
                    productBLL.updateProduct(idProduct, new Product(editProductView.getProductName(), quantity, price));
                    editProductView.showSuccess("Product with id " + idProduct + " has been successfully edited!");

                } catch (NumberFormatException ex) {
                    editProductView.showError("Make sure all the fields have been entered correctly!");
                } catch (Exception ex) {
                    editProductView.showError(ex.getMessage());
                }
            });
            editProductView.setVisible(true);
        } catch (NumberFormatException ex) {
            productView.showError("Invalid product id!");
        } catch (Exception ex) {
            productView.showError(ex.getMessage());
        }
    }
}
