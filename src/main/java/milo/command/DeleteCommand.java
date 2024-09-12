package milo.command;

import milo.lists.ClientsList;
import milo.tasks.Task;
import milo.lists.TaskList;
import milo.ui.ClientUi;
import milo.ui.TaskUi;

public class DeleteCommand extends Command {

    private final int taskIndex;
    private Task taskToDelete;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, ClientsList clientsList) {
        this.taskToDelete = taskList.get(this.taskIndex);
        taskToDelete.delete();
        taskList.remove(taskIndex);
    }

    @Override
    public String commandToString(TaskUi ui, ClientUi cUi, TaskList taskList, ClientsList clientsList) {
        return ui.printDelete(this.taskToDelete, taskList.getNumberOfTasks());
    }
}
