package sentinel.customer;

import java.util.ArrayList;

import sentinel.SentinelException;

/**
 * Represents a list of customers.
 */
public class CustomerList {
    private final ArrayList<Customer> customers;

    public CustomerList() {
        this.customers = new ArrayList<>();
    }

    /**
     * Adds a customer to the list.
     *
     * @param name Name of the customer to be added.
     * @param phoneNumber Phone number of the customer to be added.
     */
    public Customer addCustomer(String name, String phoneNumber) {
        Customer newCustomer = new Customer(name, phoneNumber);
        customers.add(newCustomer);
        return newCustomer;
    }

    /**
     * Adds a customer to the list.
     *
     * @param customer Customer to be added to the list.
     */
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    /**
     * Returns the number of customers currently in the list.
     *
     * @return Number of customers in the list.
     */
    public int getTotal() {
        return this.customers.size();
    }

    /**
     * Returns a new list of customers that matches the customer name.
     *
     * @param customerName Customers with this name to be included.
     * @return New customer list with customers matching the customer name.
     */
    public CustomerList getMatching(String customerName) {
        CustomerList newCustomerList = new CustomerList();

        for (Customer customer : customers) {
            if (customer.getName().contains(customerName)) {
                newCustomerList.addCustomer(customer);
            }
        }

        return newCustomerList;
    }

    /**
     * Deletes the customer the list.
     *
     * @param customerNumber Number of the customer.
     * @return Customer that was deleted.
     * @throws SentinelException if the customer does not exist.
     */
    public Customer deleteCustomer(int customerNumber) throws SentinelException {
        if (customerNumber > this.customers.size() || customerNumber <= 0) {
            throw new SentinelException("That customer number does not exist!");
        }
        assert(this.customers.get(customerNumber - 1) != null) : "Customer number was not found";
        return this.customers.remove(customerNumber - 1);
    }

    /**
     * Returns the string representation of the customer list.
     *
     * @return List of customers.
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < customers.size(); i++) {
            output.append(String.format("%d. %s \n", i + 1, this.customers.get(i).toString()));
        }

        return output.toString();
    }
}
