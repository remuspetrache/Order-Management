package bll;

import dao.ProductDAO;
import model.Product;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Technical University of Cluj-Napoca, Petrache Remus-Mircea
 * @since Apr 19, 2021
 */
public class ProductBLL {

    private final ProductDAO productDAO;

    /**
     * Constructor used to instantiate the attributes
     */
    public ProductBLL() {
        productDAO = new ProductDAO();
    }

    /**
     * Obtains a list with all the products in the database
     * @return list of products found in database
     */
    public List<Product> getAllProducts() {
        return productDAO.findAll();
    }

    /**
     * Adds a new product in the database
     * @param product product to be inserted
     * @return primary key of the inserted product
     */
    public int insertProduct(Product product) {
        return productDAO.insert(product);
    }

    /**
     * Updates product data of a client from the database
     * @param idProduct id of the product to be updated
     * @param product new product information
     */
    public void updateProduct(int idProduct, Product product) {
        if (productDAO.findById(idProduct) == null) {
            throw new NoSuchElementException("The product with id " + idProduct + " was not found!");
        }
        productDAO.update(idProduct, product);
    }

    /**
     * Deletes a product from the database
     * @param id id of the product to be deleted
     */
    public void deleteProduct(int id) {
        if (productDAO.findById(id) == null) {
            throw new NoSuchElementException("The product with id " + id + " was not found!");
        }
        productDAO.delete(id);
    }

    /**
     * Creates the header of the table with all products
     * @return list of column names
     */
    public List<String> createTableHeader() {
        return productDAO.createTableHeader();
    }

    /**
     * Creates the data in the table with all products
     * @return matrix with all the products' data
     */
    public List<List<Object>> createTableData() {
        return productDAO.createTableData();
    }

    /**
     * Finds a product by its id
     * @param id id to be searched
     * @return null if product was not found, otherwise found product
     */
    public Product findProductById(int id) {
        Product p = productDAO.findById(id);
        if (p == null) {
            throw new NoSuchElementException("The product with id " + id + " was not found!");
        }
        return p;
    }

}
