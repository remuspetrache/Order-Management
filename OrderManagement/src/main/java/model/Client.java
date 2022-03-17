package model;

/**
 * @author Technical University of Cluj-Napoca, Petrache Remus-Mircea
 * @since Apr 14, 2021
 */
public class Client {
    private int idClient;
    private String name;
    private String emailAddress;
    private String address;

    /**
     * Empty constructor
     */
    public Client() {
    }

    /**
     * Constructor that receives all parameters
     * @param idClient unique id for each client
     * @param name name of the client
     * @param emailAddress email address of the client
     * @param address address of the client
     */
    public Client(int idClient, String name, String emailAddress, String address) {
        super();
        this.idClient = idClient;
        this.name = name;
        this.emailAddress = emailAddress;
        this.address = address;
    }

    /**
     * Constructor that receives all parameters, except the idClient
     * @param name name of the client
     * @param emailAddress email address of the client
     * @param address address of the client
     */
    public Client(String name, String emailAddress, String address) {
        super();
        this.name = name;
        this.emailAddress = emailAddress;
        this.address = address;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Return object information as a string in order to display it
     * @return string used to display the object information
     */
    public String toString(){
        String result = "";
        result += "IdClient: " + idClient;
        result += "\nName: " + name;
        result += "\nEmail: " + emailAddress;
        result += "\nAddress: " + address;
        return result;
    }
}
