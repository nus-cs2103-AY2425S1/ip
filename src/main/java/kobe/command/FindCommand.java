package kobe.command;

import kobe.task.TaskList;
import kobe.util.Storage;
import kobe.util.Ui;

/**
 * Represents a command to find tasks based on a keyword in the task list of the Duke chatbot application.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command, which displays tasks that match the keyword.
     *
     * @param tasks   The TaskList object containing all tasks.
     * @param ui      The Ui object responsible for user interactions.
     * @param storage The Storage object responsible for saving and loading tasks (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = tasks.findTasks(keyword);
        ui.setResponse("Here are the matching tasks in your list:\n" + matchingTasks.getAllTasksAsString());
    }
}
