package sentinel.customer;

/**
 * Represents a customer.
 */
public class Customer {
    private final String name;
    private final String phoneNumber;

    /**
     * Constructor for customer.
     *
     * @param name Name for the customer.
     * @param phoneNumber Phone number of the customer.
     */
    public Customer(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return this.name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    @Override
    public String toString() {
        return String.format("%s #%s", name, phoneNumber);
    }
}
