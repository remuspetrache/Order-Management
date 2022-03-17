package dao;

import model.ClientOrder;
import model.Product;

/**
 * @author Technical University of Cluj-Napoca, Petrache Remus-Mircea
 * @since Apr 18, 2021
 */
public class OrderDAO extends AbstractDAO<ClientOrder> {

    private final ProductDAO productDAO = new ProductDAO();

    /**
     * Inserts order and updates the current product stock
     * @param t element to be inserted
     * @return primary key of the inserted element
     */
    @Override
    public int insert(ClientOrder t) {
        int insertedID = super.insert(t);
        Product product = productDAO.findById(t.getIdProduct());
        product.setQuantity(product.getQuantity() - t.getQuantity());
        productDAO.update(product.getIdProduct(), product);
        return insertedID;
    }

    /**
     * Updates order and modifies the stock of the old and the new product
     * @param id primary key of the element to be updated
     * @param t new element information
     */
    @Override
    public void update(int id, ClientOrder t) {
        ClientOrder order = this.findById(id);
        Product product = productDAO.findById(order.getIdProduct());
        product.setQuantity(product.getQuantity() + order.getQuantity());
        productDAO.update(product.getIdProduct(), product);

        super.update(id, t);

        product = productDAO.findById(t.getIdProduct());
        product.setQuantity(product.getQuantity() - t.getQuantity());
        productDAO.update(product.getIdProduct(), product);
    }

    /**
     * Deletes order and updates the stock of the product
     * @param id primary key of the element to be deleted
     */
    @Override
    public void delete(int id) {
        ClientOrder order = this.findById(id);
        Product product = productDAO.findById(order.getIdProduct());
        product.setQuantity(product.getQuantity() + order.getQuantity());
        productDAO.update(product.getIdProduct(), product);

        super.delete(id);
    }

}
