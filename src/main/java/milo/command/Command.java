package milo.command;

import milo.lists.ClientsList;
import milo.lists.TaskList;
import milo.ui.ClientUi;
import milo.ui.TaskUi;

public abstract class Command {

    boolean hasError = false;

    String errorDesc;
    public abstract void execute(TaskList taskList, ClientsList clientList);

    public abstract String commandToString(TaskUi ui, ClientUi cUi, TaskList taskList, ClientsList clientList);
}
