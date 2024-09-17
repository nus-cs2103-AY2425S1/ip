package reo.contacts;

import java.util.ArrayList;

/** Represents the saved list of contacts at the current point in time. */
public class ContactList {
    private ArrayList<Contact> contactList;

    public ContactList(ArrayList<Contact> contactList) {
        this.contactList = contactList;
    }

    public void addContact(Contact contact) {
        this.contactList.add(contact);
    }

    public void deleteContacts(ArrayList<Integer> indexes) {
        for (int i = indexes.size() - 1; i >= 0; i--) {
            int index = indexes.get(i);
            contactList.remove(index);
        }
    }
    public Contact get(int i) {
        return contactList.get(i);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < contactList.size(); i++) {
            int listIndex = i + 1;
            res.append(listIndex).append(". ").append(contactList.get(i).toString()).append("\n");
        }

        return res.toString();
    }
}
