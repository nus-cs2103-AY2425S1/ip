package milo.command;

import milo.tasks.Task;
import milo.tasks.TaskList;
import milo.ui.Ui;

public class DeleteCommand extends Command {

    private final int taskIndex;
    private Task taskToDelete;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList) {
        this.taskToDelete = taskList.get(this.taskIndex);
        taskToDelete.delete();
        taskList.remove(taskIndex);
    }

    @Override
    public String commandToString(Ui ui, TaskList taskList) {
        return ui.printDelete(this.taskToDelete, taskList.getNumberOfTasks());
    }
}
