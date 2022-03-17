package bll;

import bll.validators.QuantityValidator;
import bll.validators.Validator;
import dao.OrderDAO;
import model.ClientOrder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Technical University of Cluj-Napoca, Petrache Remus-Mircea
 * @since Apr 19, 2021
 */
public class OrderBLL {

    private final Validator<ClientOrder> validator;
    private final OrderDAO orderDAO;

    /**
     * Constructor used to instantiate the attributes
     */
    public OrderBLL() {
        validator = new QuantityValidator();
        orderDAO = new OrderDAO();
    }

    /**
     * Writes a text document that represents the bill of the order
     * @param id id of the order used to name the document
     * @param order order containing all the important data
     */
    private void writeBill(int id, ClientOrder order) {
        try {
            ClientBLL clientBLL = new ClientBLL();
            ProductBLL productBLL = new ProductBLL();
            FileWriter fileWriter = new FileWriter("Bill_Number_" + id + ".txt");
            StringBuilder sb = new StringBuilder();
            sb.append("Bill Id: ").append(id).append("\n\n");
            sb.append(clientBLL.findClientById(order.getIdClient()));
            sb.append("\n\nProduct: ").append(productBLL.findProductById(order.getIdProduct()).getName());
            sb.append("\nQuantity: ").append(order.getQuantity());
            int totalPrice = productBLL.findProductById((order.getIdProduct())).getPrice() * order.getQuantity();
            sb.append("\nTotal price: ").append(totalPrice).append("$");
            fileWriter.write(sb.toString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes the text document of a certain bill, when the order is deleted
     * @param id id of the deleted order
     */
    private void deleteBill(int id) {
        File file = new File("Bill_Number_" + id + ".txt");
        file.delete();
    }

    /**
     * Inserts a new order in the database and creates a new bill
     * @param order order to be inserted
     * @return primary key of the inserted order
     */
    public int insertOrder(ClientOrder order) {
        validator.validate(order);
        int orderId = orderDAO.insert(order);
        writeBill(orderId, order);
        return orderId;
    }

    /**
     * Updates a given order's data and its bill
     * @param idOrder id of the order to be updated
     * @param order new order information
     */
    public void updateOrder(int idOrder, ClientOrder order) {
        if (orderDAO.findById(idOrder) == null) {
            throw new NoSuchElementException("The order with id " + idOrder + " was not found!");
        }
        validator.validate(order);
        orderDAO.update(idOrder, order);
        writeBill(idOrder, order);
    }

    /**
     * Deletes a given order and its bill
     * @param id id of the order to be deleted
     */
    public void deleteOrder(int id) {
        if (orderDAO.findById(id) == null) {
            throw new NoSuchElementException("The order with id " + id + " was not found!");
        }
        orderDAO.delete(id);
        deleteBill(id);
    }

    /**
     * Finds a specific order, by its id
     * @param id id of the order to be searched
     * @return null if order hasn't been found, otherwise the found order
     */
    public ClientOrder findOrderById(int id) {
        ClientOrder o = orderDAO.findById(id);
        if (o == null) {
            throw new NoSuchElementException("The order with id " + id + " was not found!");
        }
        return o;
    }

    /**
     * Creates the header of the table with all orders
     * @return list of column names
     */
    public List<String> createTableHeader() {
        return orderDAO.createTableHeader();
    }

    /**
     * Creates the data in the table with all orders
     * @return matrix with all the orders' data
     */
    public List<List<Object>> createTableData() {
        return orderDAO.createTableData();
    }

}
