package milo.command;

import milo.lists.ClientsList;
import milo.tasks.Task;
import milo.lists.TaskList;
import milo.ui.ClientUi;
import milo.ui.TaskUi;

public class UnmarkCommand extends Command {

    private final int taskIndex;
    private Task unmarkedTask;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, ClientsList clientsList) {
        this.unmarkedTask = taskList.get(this.taskIndex);
        unmarkedTask.unmark();
    }

    @Override
    public String commandToString(TaskUi ui, ClientUi cUi, TaskList taskList, ClientsList clientsList) {
        return ui.printUnmark(this.unmarkedTask);
    }
}
