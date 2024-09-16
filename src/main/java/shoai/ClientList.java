package shoai;

import java.util.ArrayList;

public class ClientList {
    private ArrayList<Client> clients;

    public ClientList() {
        this.clients = new ArrayList<>();
    }

    public ClientList(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public void removeClient(int index) {
        if (index >= 0 && index < clients.size()) {
            clients.remove(index);
        } else {
            throw new IndexOutOfBoundsException("Invalid client index.");
        }
    }

    public Client getClient(int index) {
        if (index >= 0 && index < clients.size()) {
            return clients.get(index);
        } else {
            throw new IndexOutOfBoundsException("Invalid client index.");
        }
    }

    public ArrayList<Client> getAllClients() {
        return clients;
    }
}
