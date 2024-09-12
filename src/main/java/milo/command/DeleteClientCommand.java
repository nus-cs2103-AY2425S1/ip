package milo.command;

import milo.clients.Client;
import milo.lists.ClientsList;
import milo.lists.TaskList;
import milo.ui.ClientUi;
import milo.ui.TaskUi;

public class DeleteClientCommand extends Command {

    private final int clientIndex;
    private Client clientToDelete;

    public DeleteClientCommand(int clientIndex) {
        this.clientIndex = clientIndex;
    }

    @Override
    public void execute(TaskList taskList, ClientsList clientList) {
        this.clientToDelete = clientList.get(this.clientIndex);
        clientList.remove(clientIndex);
    }

    @Override
    public String commandToString(TaskUi ui, ClientUi cUi, TaskList taskList, ClientsList clientList) {
        return cUi.printDeleteClient(clientToDelete, clientList.getNumberOfClients());
    }
}
