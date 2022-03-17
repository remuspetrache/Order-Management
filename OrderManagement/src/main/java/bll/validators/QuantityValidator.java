package bll.validators;

import dao.ProductDAO;
import model.ClientOrder;

/**
 * @author Technical University of Cluj-Napoca, Petrache Remus-Mircea
 * @since Apr 16, 2021
 */
public class QuantityValidator implements Validator<ClientOrder> {

    private final ProductDAO productDAO = new ProductDAO();

    /**
     * Validates quantity of the order
     * @param order order that has to be verified
     */
    @Override
    public void validate(ClientOrder order) {
        int currentStock = productDAO.findById(order.getIdProduct()).getQuantity();
        if (currentStock < order.getQuantity()) {
            if (currentStock == 0) {
                throw new IllegalArgumentException("There are no products in stock!");
            } else if (currentStock == 1) {
                throw new IllegalArgumentException("There is 1 product in stock!");
            } else {
                throw new IllegalArgumentException("There are only " + currentStock + " products in stock!");
            }
        }
    }
}
