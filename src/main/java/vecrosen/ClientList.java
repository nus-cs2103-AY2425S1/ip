package vecrosen;

import java.io.File;
import java.util.ArrayList;

public class ClientList {
    private ArrayList<Client> list;

    /**
     * Initializes an empty list of clients.
     */
    public ClientList() {
        this(new ArrayList<Client>());
    }

    /**
     * Initializes the client list with the supplied list of clients.
     * The contents of the list will be deep-copied.
     *
     * @param list The ArrayList containing the initial list of clients and to be used for storing the current state.
     */
    public ClientList(ArrayList<Client> list) {
        this.list = list;
    }

    /**
     * Initializes the list of clients using the data from the file.
     *
     * @param file The file to load from.
     */
    public ClientList(File file) {
        list = new ArrayList<Client>();
        Storage.loadClients(file, list);
    }

    /**
     * Adds a client to the list of clients.
     *
     * @param c The client to be added
     */
    public void addClient(Client c) {
        list.add(c);
    }

    /**
     * Removes a client from the list of clients.
     *
     * @param index The index of the client to be removed, 1-indexed
     * @return The name of the removed client
     */
    public String deleteClient(int index) {
        index = index - 1;
        String rdesc = list.get(index).getName();
        list.remove(index);
        return rdesc;
    } // to be implemented

    /**
     * Saves the contents of the list to the specified file
     *
     * @param file
     */
    public void save(File file) {
        Storage.saveClients(file, list);
    }

    /**
     * Prints out the contents of the list to the UI.
     *
     * @param ui The UI object to print to.
     */
    public void printList(Ui ui) {
        ArrayList<Integer> indices = new ArrayList<Integer>();
        for (int i = 1; i <= list.size(); ++i) {
            indices.add(i);
        }
        ui.<Client>printList(list, indices, "Here are your known clients:", "There are no client records!");
    }

    /**
     * Finds all clients that contain the string given in their name. Case-sensitive.
     *
     * @param s The keyword to search by
     * @param indices Array to hold the indices of the found clients
     * @return The clients matching the query
     */
    public ArrayList<Client> find(String s, ArrayList<Integer> indices) {
        ArrayList<Client> res = new ArrayList<Client>();
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i).getName().contains(s)) {
                res.add(list.get(i));
                indices.add(i + 1);
            }
        }
        return res;
    }

    /**
     * Returns the number of clients in the list.
     *
     * @return Number of clients in the list.
     */
    public int getListSize() {
        return list.size();
    }
}
