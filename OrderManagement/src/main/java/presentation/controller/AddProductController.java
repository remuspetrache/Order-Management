package presentation.controller;

import bll.ProductBLL;
import model.Product;
import presentation.view.AddProductView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Technical University of Cluj-Napoca, Petrache Remus-Mircea
 * @since Apr 19, 2021
 */
public class AddProductController implements ActionListener {
    private final ProductBLL productBLL;

    /**
     * Instantiates the productBLL parameter
     */
    public AddProductController() {
        productBLL = new ProductBLL();
    }

    /**
     * Adds a new product and displays success message if valid, otherwise displays an error message
     * @param e click on the add button
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        AddProductView addProductView = new AddProductView();
        addProductView.addSubmitListener(e1 -> {
            try {
                int quantity = Integer.parseInt(addProductView.getQuantity());
                int price = Integer.parseInt(addProductView.getPrice());
                if (addProductView.getProductName().equals("") || quantity < 0 || price < 0) {
                    throw new NumberFormatException();
                }
                int productId = productBLL.insertProduct(new Product(addProductView.getProductName(), quantity, price));
                addProductView.showSuccess("Product with id " + productId + " has been successfully added!");

            } catch (NumberFormatException ex) {
                addProductView.showError("Make sure all the fields have been entered correctly!");
            } catch (Exception ex) {
                addProductView.showError(ex.getMessage());
            }

        });
        addProductView.setVisible(true);
    }
}
