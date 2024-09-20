package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to find tasks that contain a keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Creates a new FindCommand with the given keyword.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command to find tasks that contain the keyword.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to save and load tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = tasks.findTasks(keyword);
        return ui.showFoundTasks(matchingTasks);
    }
}
