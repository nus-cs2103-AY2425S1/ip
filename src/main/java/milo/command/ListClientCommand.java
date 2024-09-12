package milo.command;

import milo.lists.ClientsList;
import milo.lists.TaskList;
import milo.ui.ClientUi;
import milo.ui.TaskUi;

public class ListClientCommand extends Command {

    @Override
    public void execute(TaskList taskList, ClientsList clientList) {
        return;
    }

    @Override
    public String commandToString(TaskUi ui, ClientUi cUi, TaskList taskList, ClientsList clientList) {
        return cUi.printListClient(clientList);
    }
}
