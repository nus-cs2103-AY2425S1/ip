package darkpool.command;

import darkpool.gui.Gui;
import darkpool.task.Task;
import darkpool.util.Storage;
import darkpool.util.TaskList;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {

    private final Task task;

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
     * @param gui       The UI to update.
     * @param storage  The storage to save the task list.
     * @return
     */
    @Override
    public String execute(TaskList taskList, Gui gui, Storage storage) {
        taskList.addTask(task);
        return gui.add(String.valueOf(task), taskList.getSize());
    }

}
