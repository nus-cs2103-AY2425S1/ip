package loafy.command;

import loafy.tasklist.TaskList;
import loafy.ui.Ui;

/**
 * Represents a command to find a task by its name in the task list.
 */
public class FindCommand extends Command {

    private final String keyword;

    /**
     * Constructs a find command for the specified keyword.
     *
     * @param keyword the keyword to be used to find the task.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds the task which name contains the keyword from the specified task list.
     * Returns a display of all tasks containing the keyword with their id in the list.
     * If no matches are found, an error message is returned to inform the user.
     *
     * @param tasks Task list from which the task will be found.
     * @param ui User interface.
     * @return the message to be viewed by the user.
     */
    public String execute(TaskList tasks, Ui ui) {
        return tasks.find(this.keyword);
    }
}
