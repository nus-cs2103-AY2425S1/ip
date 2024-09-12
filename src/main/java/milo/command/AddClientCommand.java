package milo.command;

import milo.clients.Client;
import milo.lists.ClientsList;
import milo.lists.TaskList;
import milo.ui.ClientUi;
import milo.ui.TaskUi;

public class AddClientCommand extends Command {

    private final String[] clientDesc;
    private Client curClient;

    public AddClientCommand(String[] clientDesc) {
        this.clientDesc = clientDesc;
    }

    @Override
    public void execute(TaskList taskList, ClientsList clientsList) {
        String[] arrClientDesc = clientDesc[1].split(" ", 2);
        this.curClient = new Client(arrClientDesc[0], arrClientDesc[1]);
        clientsList.add(this.curClient);
    }

    @Override
    public String commandToString(TaskUi ui, ClientUi cUi, TaskList taskList, ClientsList clientsList) {
        return cUi.printAddClient(curClient, clientsList.getNumberOfClients());
    }
}
