package model;

/**
 * @author Technical University of Cluj-Napoca, Petrache Remus-Mircea
 * @since Apr 14, 2021
 */
public class ClientOrder {
    private int idOrder;
    private int idClient;
    private int idProduct;
    private int quantity;

    /**
     * Empty constructor
     */
    public ClientOrder() {
    }

    /**
     * Constructor that receives all parameters
     * @param idOrder unique id for each order
     * @param idClient id taken from the list of clients' ids
     * @param idProduct id taken from the list of products' ids
     * @param quantity quantity of products in the order
     */
    public ClientOrder(int idOrder, int idClient, int idProduct, int quantity) {
        super();
        this.idOrder = idOrder;
        this.idClient = idClient;
        this.idProduct = idProduct;
        this.quantity = quantity;
    }

    /**
     * Constructor that receives all parameters, except the idOrder
     * @param idClient id taken from the list of clients' ids
     * @param idProduct id taken from the list of products' ids
     * @param quantity quantity of products in the order
     */
    public ClientOrder(int idClient, int idProduct, int quantity) {
        super();
        this.idClient = idClient;
        this.idProduct = idProduct;
        this.quantity = quantity;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
