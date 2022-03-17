package bll;

import java.util.List;
import java.util.NoSuchElementException;

import bll.validators.EmailValidator;
import bll.validators.Validator;
import dao.ClientDAO;
import model.Client;

/**
 * @author Technical University of Cluj-Napoca, Petrache Remus-Mircea
 * @since Apr 19, 2021
 */
public class ClientBLL {

    private final Validator<Client> validator;
    private final ClientDAO clientDAO;

    /**
     * Constructor used to instantiate the attributes
     */
    public ClientBLL() {
        validator = new EmailValidator();
        clientDAO = new ClientDAO();
    }

    /**
     * Obtains a list with all the clients in the database
     * @return list of clients found in database
     */
    public List<Client> getAllClients() {
        return clientDAO.findAll();
    }

    /**
     * Adds a new client in the database
     * @param client client to be inserted
     * @return primary key of the inserted client
     */
    public int insertClient(Client client) {
        validator.validate(client);
        return clientDAO.insert(client);
    }

    /**
     * Updates client data of a client from the database
     * @param idClient primary key of the client to be updated
     * @param client new client data
     */
    public void updateClient(int idClient, Client client) {
        if (clientDAO.findById(idClient) == null) {
            throw new NoSuchElementException("The client with id " + idClient + " was not found!");
        }
        validator.validate(client);
        clientDAO.update(idClient, client);
    }

    /**
     * Deletes a client from the database with a given id
     * @param id primary key of the client to be deleted
     */
    public void deleteClient(int id) {
        if (clientDAO.findById(id) == null) {
            throw new NoSuchElementException("The client with id " + id + " was not found!");
        }
        clientDAO.delete(id);
    }

    /**
     * Finds a client by its id
     * @param id id to be searched
     * @return null if client was not found, otherwise the found client
     */
    public Client findClientById(int id) {
        Client c = clientDAO.findById(id);
        if (c == null) {
            throw new NoSuchElementException("The client with id " + id + " was not found!");
        }
        return c;
    }

    /**
     * Creates the header of the table with all clients
     * @return list of column names
     */
    public List<String> createTableHeader() {
        return clientDAO.createTableHeader();
    }

    /**
     * Creates the data in the table with all clients
     * @return matrix with all the clients' data
     */
    public List<List<Object>> createTableData() {
        return clientDAO.createTableData();
    }

}
