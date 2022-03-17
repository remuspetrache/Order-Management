package presentation.controller;

import presentation.view.ProductView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Technical University of Cluj-Napoca, Petrache Remus-Mircea
 * @since Apr 19, 2021
 */
public class ShowProductController implements ActionListener {

    /**
     * Displays the product view and adds its action listener
     * @param e click on products button
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ProductView productView = new ProductView();
        productView.addAddListener(new AddProductController());
        productView.addEditListener(new EditProductController(productView));
        productView.addDeleteListener(new DeleteProductController(productView));
        productView.addViewListener(new ShowTableController("Product"));
        productView.setVisible(true);
    }
}
