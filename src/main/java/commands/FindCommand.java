package commands;

import exceptions.DownyException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * The {@code FindCommand} class represents a command that searches for tasks in the task list
 * that contain a specified keyword. The search is case-insensitive.
 */
public class FindCommand implements Command {

    private final String keyword;

    /**
     * Constructs a new {@code FindCommand} with the specified keyword.
     *
     * @param keyword The keyword to search for in the task names.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by loading tasks from storage and displaying
     * tasks that contain the specified keyword. The search is case-insensitive.
     *
     * @param storage The storage handler used for loading tasks.
     * @param tasks   The list of tasks to search through.
     * @param ui      The UI handler used to display the matching tasks.
     * @throws DownyException If an error occurs during the execution of the command.
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) throws DownyException {
        tasks.loadTasks(storage);
        return ui.displayMatchingTasks(tasks, keyword);
    }
}
