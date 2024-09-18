package commands;

import exceptions.DownyException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * The {@code ListCommand} class represents a command that, when executed, loads the tasks
 * from storage and displays them to the user.
 */
public class ListCommand implements Command {

    /**
     * Executes the List command by loading tasks from storage into the task list and
     * displaying them to the user. If the task list is empty, it displays a message
     * indicating that there are no tasks.
     *
     * @param storage The storage handler used for loading tasks from persistent storage.
     * @param tasks   The list of tasks currently in memory.
     * @param ui      The UI handler used for interacting with the user.
     * @throws DownyException If an error occurs during the loading of tasks from storage.
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) throws DownyException {
        tasks.loadTasks(storage);
        if (tasks.isEmpty()) {
            return Ui.showMessage("There are no tasks!");
        }
        return ui.displayTasks(tasks);
    }
}
