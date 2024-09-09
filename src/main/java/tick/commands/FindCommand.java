package tick.commands;

import java.util.ArrayList;

import tick.storage.Storage;
import tick.storage.TaskList;
import tick.tasks.Task;
import tick.ui.Ui;

/**
 * Represents a command to find tasks with a keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand object with the specified keyword.
     *
     * @param keyword The keyword to search for in list of tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command to search for tasks with the specified keyword.
     *
     * @param taskList The TaskList containing all tasks.
     * @param ui The Ui object to handle user interactions.
     * @param storage The Storage object to handle data storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> tasksWithKeyword = taskList.findTasks(keyword);
        ui.showMatchingTasks(tasksWithKeyword);
    }

    /**
     * Returns false as the command is not an exit command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
