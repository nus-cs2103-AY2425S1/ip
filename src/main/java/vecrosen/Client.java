package vecrosen;

/**
 * Represents a client added into the database.
 */
public class Client {
    private String name;
    private String address;

    /**
     * Creates a new record of a client
     * @param name The client's name
     * @param address The client's address
     */
    public Client(String name, String address) {
        this.name = name;
        this.address = address;
    }

    /**
     * Getter method for name
     * @return The client's name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for address
     * @return The client's address
     */
    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return this.name + ", lives at " + this.address;
    }
}
