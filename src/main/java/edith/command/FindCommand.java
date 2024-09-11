package edith.command;

import edith.Ui;
import edith.Storage;
import edith.EdithException;
import edith.task.TaskList;

/**
 * Represents a command to find tasks in the task list that match a given keyword.
 * The FindCommand class searches for task descriptions with the specified keyword and lists
 * the matching tasks.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     * The keyword is stored in lowercase to enable case-insensitive search.
     *
     * @param keyword The keyword to search for in the task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    /**
     * Executes the FindCommand by listing tasks that contain
     * the specified keyword in their description.
     *
     * <p>This method will:
     * <ul>
     *     <li>Retrieve and display tasks from the TaskList that contain the specified keyword in their description.</li>
     * </ul>
     *
     * @param tasks The TaskList containing all tasks to be listed.
     * @param ui The Ui used to display the tasks matching the specified keyword.
     * @param storage The Storage used to save changes (not used in this command).
     * @throws EdithException This method does not throw any EdithException.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws EdithException {
        return tasks.findTasksByKeyword(keyword, ui);
    }
}
