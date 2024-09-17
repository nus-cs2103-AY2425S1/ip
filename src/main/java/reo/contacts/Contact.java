package reo.contacts;

/** Represents the contact information of 1 person. */
public class Contact {
    private String name;
    private String phoneNumber;
    private String address;

    public Contact(String name, String phoneNumber, String address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    @Override
    public String toString() {
        final String SPACER = " | ";
        return name + SPACER + phoneNumber + SPACER + address;
    }
}
