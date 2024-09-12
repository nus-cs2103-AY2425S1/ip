package milo.ui;

import milo.clients.Client;
import milo.lists.ClientsList;

public class ClientUi extends Ui{

    public String printAddClient(Client curClient, int numberOfClients) {
        StringBuilder response = new StringBuilder();
        response.append("Got it. I've added this client:\n");
        response.append("  ").append(curClient).append("\n");
        if (numberOfClients == 1) {
            response.append("Now you have ").append(numberOfClients).append(" clients in your contacts.\n");
        } else {
            response.append("Now you have ").append(numberOfClients).append(" clients in your contacts.\n");
        }
        return response.toString();
    }

    public String printListClient(ClientsList clientList) {
        StringBuilder response = new StringBuilder();
        response.append("Here are your clients:\n");
        for (int i = 0; i < clientList.getNumberOfClients(); i++) {
            response.append(i + 1).append(".").append(clientList.get(i).toString()).append("\n");
        }
        return response.toString();
    }

    public String printDeleteClient(Client curClient, int numberOfClients) {
        StringBuilder response = new StringBuilder();
        response.append("Noted. I've removed this client:\n");
        response.append("  ").append(curClient).append("\n");
        if (numberOfClients == 1) {
            response.append("Now you have ").append(numberOfClients).append(" task in your contacts.\n");
        } else {
            response.append("Now you have ").append(numberOfClients).append(" clients in your contacts.\n");
        }
        return response.toString();
    }

}
