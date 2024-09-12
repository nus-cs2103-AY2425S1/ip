package milo.storage;

import milo.clients.Client;
import milo.lists.ClientsList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientStorage extends Storage<Client> {

    private final String clientFilePath;
    private final ArrayList<Client> clientsList = new ArrayList<>();

    public ClientStorage(String clientFilePath) {
        this.clientFilePath = clientFilePath;
    }

    @Override
    public ArrayList<Client> readData() {
        try {
            File f = new File(this.clientFilePath);
            Scanner s = new Scanner(f);
            // Iterates through lines in a file
            while (s.hasNext()) {
                // Format each text line into a client
                Client curClient = formatterToClient(s.nextLine());
                // Adds each client to the array list
                this.clientsList.add(curClient);
            }
            return this.clientsList;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }


    private Client formatterToClient(String client) {
        String[] clientDesc = client.split("\\|");
        return new Client(clientDesc[0], clientDesc[1]);
    }

    private String formatterToText(Client client) {
        return client.toTextString();
    }

    public void saveClientData(ClientsList clientsList) {
        System.out.println("running saveClientData");
        try {
            FileWriter fw = new FileWriter(this.clientFilePath);
            for (int i = 0; i < clientsList.getNumberOfClients(); i++) {
                System.out.println("saving " + i + "th item");
                fw.write(formatterToText(clientsList.get(i)) + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
