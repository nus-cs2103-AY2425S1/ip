package rose.command;

import rose.Storage;
import rose.TaskList;
import rose.Ui;

/**
 * Represents a command used by the user to find tasks containing a specific keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command to search for tasks containing the keyword.
     *
     * @param tasks The TaskList to search through.
     * @param ui The Ui instance for displaying messages to the user.
     * @param storage The Storage instance, not used in this method.
     * @return A message displaying the tasks that match the search keyword.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.findTask(this.keyword, ui);
    }
}
