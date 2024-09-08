package justbot.command;

import justbot.storage.Storage;
import justbot.task.Task;
import justbot.task.TaskList;
import justbot.ui.Ui;

/**
 * Represents a command that finds tasks in a task list that contain a specific keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in the task list.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command, searching the provided task list for tasks that contain the keyword.
     * The results are then displayed via the user interface.
     *
     * @param taskList The list of tasks to search through.
     * @param ui       The user interface to display the results.
     * @param storage  The storage system to save any changes (not used in this command).
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.findMessage(taskList, keyword);
    }

    /**
     * Returns null as this command does not return a specific task.
     *
     * @return null, as this command does not return a specific task.
     */
    @Override
    public Task getTask() {
        return null;
    }
}
