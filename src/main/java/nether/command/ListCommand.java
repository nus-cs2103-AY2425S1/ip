package nether.command;

import nether.Ui;
import nether.storage.Storage;
import nether.task.TaskList;

/**
 * Represents a command to list all tasks in the task list.
 * <p>
 * The {@code ListCommand} class extends {@code Command} and provides the implementation for
 * displaying the list of tasks to the user.
 * </p>
 */
public class ListCommand extends Command {
    /**
     * Executes the command to list all tasks.
     * <p>
     * This method calls the {@code printList} method of the {@code TaskList} class to display
     * the current list of tasks to the user.
     * </p>
     *
     * @param tasks The {@code TaskList} object that contains all tasks.
     * @param ui The {@code Ui} object used for printing the task list to the user.
     * @param storage The {@code Storage} object (not used in this method).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printList();
    }
}
