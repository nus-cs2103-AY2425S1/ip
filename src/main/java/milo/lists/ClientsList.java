package milo.lists;

import milo.clients.Client;

import java.util.ArrayList;

public class ClientsList extends List<Client>{
    private final ArrayList<Client> clientsList;

    private int numberOfClients;

    /**
     * Initialise empty clients list object
     */
    public ClientsList() {
        this.clientsList = new ArrayList<Client>();
    }

    public ClientsList(ArrayList<Client> clientsList) {
        this.numberOfClients = clientsList.size();
        this.clientsList = clientsList;
    }

    public int getNumberOfClients() {
        return numberOfClients;
    }

    @Override
    public void add(Client item) {
        this.clientsList.add(item);
        this.numberOfClients++;
    }

    @Override
    public Client get(int index) {
        return this.clientsList.get(index);
    }

    @Override
    public void remove(int index) {
        this.clientsList.remove(index);
        this.numberOfClients--;
    }
}
