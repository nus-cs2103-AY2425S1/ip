package milo.command;

import milo.lists.ClientsList;
import milo.lists.TaskList;
import milo.ui.ClientUi;
import milo.ui.TaskUi;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, ClientsList clientsList) {
        return;
    }

    @Override
    public String commandToString(TaskUi ui, ClientUi cUi, TaskList taskList, ClientsList clientsList) {
        return ui.printTaskList(taskList);
    }
}
