package darkpool.command;

import darkpool.task.Task;
import darkpool.util.Storage;
import darkpool.util.TaskList;
import darkpool.util.Ui;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {

    private Task task;

    /**
     * Constructs an AddCommand with the specified task.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add command, adding the task to the task list and updating the UI.
     *
     * @param taskList The task list to add the task to.
     * @param ui The UI to update.
     * @param storage The storage to save the task list.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);
        ui.add(String.valueOf(task), taskList.getSize());
    }

}