package command;

import components.Storage;
import components.Ui;
import exceptions.LightException;
import task.TaskList;

/**
 * Represents a command to find tasks in the task list.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Creates a new FindCommand with the given keyword.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command to find tasks in the task list.
     *
     * @param tasks   The task list to find tasks from.
     * @param ui      The user interface to display messages.
     * @param storage The storage to save the task list to.
     * @throws LightException If an error occurs while finding tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LightException {
        TaskList foundTasks = tasks.findTasks(keyword);
        ui.showMessage(TaskList.arrayToNumberedString(foundTasks));
    }
}
