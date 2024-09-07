package reo.contacts;

import reo.storage.ContactStorage;

import java.util.ArrayList;
import java.util.Arrays;

public class ContactParser {
    private String input;
    private String[] words;
    private ContactParser.Command command;
    private ContactList contacts;
    private ContactStorage storage;

    enum Command {
        PERSON,
        CONTACTS,
        DELETECONTACT,
        UNKNOWN
    }

    public ContactParser(String input, ContactList contacts, ContactStorage storage) {
        this.input = input;
        this.contacts = contacts;
        this.words = input.split("\\s+");
        this.storage = storage;

        try {
            this.command = ContactParser.Command.valueOf(words[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            this.command = ContactParser.Command.UNKNOWN;
        }
    }

    public String parse() {
        switch (command) {
        case PERSON:
            return handlePerson();
        case CONTACTS:
            return handleContacts();
        case DELETECONTACT:
            return handleDelete();
        default:
            return null;
        }
    }

    private String handleContacts() {
        return contacts.toString();
    }

    private String[] parsePerson(String input) {
        String[] parts = input.split("/n", 2);
        String[] firstPart = parts[0].split(" ", 2);
        String[] secondPart = parts[1].split("/a", 2);

        String name = firstPart[1].trim();
        String number = secondPart[0].trim();
        String address = secondPart[1].trim();

        if (name.equals("") || number.equals("") || address.equals("")) {
            throw new IllegalArgumentException();
        }
        return new String[]{name, number, address};
    }

    private String handlePerson() {
        final String PERSON_ERROR = "ERROR: Please enter a valid name, phone number and address.";
        try {
            final String PERSON_CONFIRMATION = "I've added this contact:\n";
            String name = parsePerson(input)[0];
            String number = parsePerson(input)[1];
            String address = parsePerson(input)[2];

            Contact toPush = new Contact(name, number, address);
            contacts.addContact(toPush);
            storage.writeFile(toPush);
            return PERSON_CONFIRMATION + toPush.toString();
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            return PERSON_ERROR;
        }
    }

    private String handleDelete() {
        final String DELETE_CONFIRMATION = "I've deleted these contacts:\n";
        final String DELETE_ERROR = "Please enter one or more valid contact numbers.\n";
        try {
            ContactList toDelete = new ContactList(new ArrayList<Contact>());
            ArrayList<Integer> indexes = new ArrayList<>();
            Arrays.sort(words, 1, words.length);
            for (int i = 1; i < words.length; i++) {
                int index = Integer.valueOf(words[i]) - 1;
                indexes.add(index);
                toDelete.addContact(contacts.get(index));
                storage.removeLine(index + 1);
            }
            contacts.deleteContacts(indexes);
            return DELETE_CONFIRMATION + toDelete.toString();
        } catch (IndexOutOfBoundsException e) {
            return DELETE_ERROR;
        }
    }
}
