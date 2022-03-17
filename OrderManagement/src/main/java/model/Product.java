package model;

/**
 * @author Technical University of Cluj-Napoca, Petrache Remus-Mircea
 * @since Apr 14, 2021
 */
public class Product {
    private int idProduct;
    private String name;
    private int quantity;
    private int price;

    /**
     * Empty constructor
     */
    public Product() {
    }

    /**
     * Constructor that receives all parameters
     * @param idProduct unique id for each product
     * @param name name of the product
     * @param quantity stock quantity of the product
     * @param price price per unit of the product
     */
    public Product(int idProduct, String name, int quantity, int price) {
        super();
        this.idProduct = idProduct;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * Constructor that receives all parameters, except the idProduct
     * @param name name of the product
     * @param quantity stock quantity of the product
     * @param price price per unit of the product
     */
    public Product(String name, int quantity, int price) {
        super();
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
