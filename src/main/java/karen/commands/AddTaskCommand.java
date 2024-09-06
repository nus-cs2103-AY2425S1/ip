package karen.commands;

import karen.tasks.Task;
import karen.tasks.TaskList;
import karen.util.Storage;
import karen.util.Ui;

/**
 * Handles adding a new <code>Task</code> to the <code>TaskList</code> and prints the appropriate message
 */
public class AddTaskCommand extends Command {
    private Task task;

    public AddTaskCommand(Task t) {
        this.task = t;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.addTask(this.task);
        ui.showAddTaskMessage(this.task, taskList);
        Storage.saveToFile(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
