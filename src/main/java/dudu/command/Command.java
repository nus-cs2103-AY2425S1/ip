package dudu.command;

import dudu.exception.DuduException;
import dudu.utils.Storage;
import dudu.utils.TaskList;
import dudu.utils.UI;

/**
 * Represents an abstract user command.
 */
public abstract class Command {
    /**
     * Executes the command with the provided task list, user interface and storage.
     *
     * @param taskList Task list containing the tasks.
     * @param ui User interface to interact with the user.
     * @param storage Storage to save tasks.
     * @throws DuduException If there is an error during rewriting the local file in storage.
     */
    public abstract String execute(TaskList taskList, UI ui, Storage storage) throws DuduException;
}
