package Naega.Command;

import Naega.Storage.Storage;
import Naega.Task.Task;
import Naega.Task.TaskList;
import Naega.Ui.Ui;

import java.util.List;

/**
 * Represents a command to find and display tasks that contain a specific keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Creates a FindCommand with the specified keyword.
     *
     * @param keyword the keyword to search for in tasks
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command by finding tasks that match the keyword and displaying them using the UI.
     *
     * @param tasks   the task list to search within
     * @param ui      the UI component to display the found tasks
     * @param storage the storage component (not used in this command)
     * @return a string message that lists the tasks that match the keyword, or a message indicating no tasks were found
     *  */

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matchingTasks = tasks.findTasksByKeyword(keyword);
        return ui.showFoundTasks(matchingTasks);
    }
}