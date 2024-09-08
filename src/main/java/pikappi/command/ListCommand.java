package pikappi.command;

import pikappi.Storage;
import pikappi.TaskList;
import pikappi.Ui;

/**
 * Represents a command by user to list all tasks.
 */
public class ListCommand extends Command {
    /**
     * Lists all tasks in the list of tasks.
     *
     * @param tasks List of tasks.
     * @param ui Ui object that interacts with the user.
     * @param storage The storage object.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList cannot be null";
        return tasks.listTasks();
    }
}
