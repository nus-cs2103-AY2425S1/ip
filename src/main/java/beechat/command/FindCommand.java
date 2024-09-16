package beechat.command;

import beechat.util.Storage;
import beechat.task.TaskList;
import beechat.util.Ui;

/**
 * Represents a command to find all tasks containing the keyword.
 *
 */
public class FindCommand extends Command {

    /** Keyword to find tasks */
    private final String keyword;

    /**
     * Constructs a FindCommand using the specified keyword.
     *
     * @param keyword The keyword in the user input.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command, which finds all tasks with the keyword in the description.
     *
     * @param tasks   The TaskList object that stores all tasks.
     * @param ui      The Ui object that handles user interactions.
     * @param storage The Storage object that loads and saves tasks to the file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = tasks.findTasks(keyword);
        ui.showFindResults(matchingTasks);
    }
}
}
