package darkpool.command;

import darkpool.util.Storage;
import darkpool.util.TaskList;
import darkpool.gui.Gui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command, removing the task from the task list and updating the UI.
     *
     * @param tasks   The task list from which the task will be deleted.
     * @param gui      The UI to update.
     * @param storage The storage to save the updated task list.
     * @return
     */
    @Override
    public String execute(TaskList tasks, Gui gui, Storage storage) {
        String delString = tasks.deleteTask(index);
        return gui.delete(delString);
    }

}
