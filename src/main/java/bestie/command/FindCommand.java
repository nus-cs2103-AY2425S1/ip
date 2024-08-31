package bestie.command;

import java.util.ArrayList;

import bestie.Storage;
import bestie.TaskList;
import bestie.Ui;
import bestie.task.Task;

/**
 * Creates a command that represents user command of finding tasks with matching keyword.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Finds all task with a matching keyword and displays them on the console, in a list format.
     *
     * @param keyword The keyword that the user wants to use to find specific tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Simulates execution of find command, displays matching task to user.
     *
     * @param tasks List of all tasks.
     * @param ui User interface to display the matching tasks.
     * @param storage Contains tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = tasks.findMatchingTasks(this.keyword);
        ui.showFoundTasks(matchingTasks);
    }

}
