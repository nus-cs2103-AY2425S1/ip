package Chatbot;

import java.util.HashMap;
import java.util.Map;

public class ContactStorage {
    private Map<String, String> contacts;

    public ContactStorage() {
        this.contacts = new HashMap<>();
    }

    /**
     * Adds a contact to the storage.
     *
     * @param name    the contact name.
     * @param details the contact details.
     */
    public void addContact(String name, String details) {
        contacts.put(name, details);
    }

    /**
     * Retrieves the contact details for a given contact name.
     *
     * @param name the contact name.
     * @return the contact details, or null if the contact does not exist.
     */
    public String getContactDetails(String name) {
        return contacts.get(name);
    }

    /**
     * Deletes a contact from the storage.
     *
     * @param name the contact name to be deleted.
     */
    public void deleteContact(String name) {
        contacts.remove(name);
    }

    /**
     * Lists all contacts in the storage.
     *
     * @return a map of all contacts with their corresponding details.
     */
    public String listContacts() {
        StringBuilder sb = new StringBuilder();

        // Check if there are any contacts
        if (contacts.isEmpty()) {
            return "No contacts available.";
        }

        // Iterate through the map and append each contact to the StringBuilder
        for (Map.Entry<String, String> entry : contacts.entrySet()) {
            sb.append("Name: ").append(entry.getKey())
                    .append(", Details: ").append(entry.getValue())
                    .append("\n");
        }

        // Convert StringBuilder content to a string and return it
        return sb.toString();
    }
}
