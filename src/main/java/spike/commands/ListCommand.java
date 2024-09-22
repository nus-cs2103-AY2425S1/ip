package spike.commands;

import spike.storage.Storage;
import spike.storage.TaskList;
import spike.ui.Ui;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the command to list all tasks.
     *
     * @param tasks   The task list with all the tasks to be listed.
     * @param ui      The user interface to interact with the user.
     * @param storage The storage to write the task list to the file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "Task list cannot be null";
        assert ui != null : "User interface cannot be null";
        ui.showTaskList(tasks.getAllTasks());
    }

    /**
     * Returns whether the command is an exit command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
