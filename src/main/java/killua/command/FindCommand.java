package killua.command;

import killua.task.Task;
import killua.util.Storage;
import killua.util.TaskList;
import killua.util.Ui;

import java.util.ArrayList;

/**
 * Represents a command to find and display tasks that match a specified keyword.
 * This command filters tasks based on the keyword and shows the matching tasks to the user.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     * Initializes the keyword field with the given value.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Filters the tasks to find those whose descriptions match the keyword.
     * Creates a new TaskList containing only the tasks that match the keyword.
     *
     * @param tasks The task list to search through.
     * @return A TaskList containing tasks that match the keyword.
     */
    private TaskList filterTask(TaskList tasks) {
        ArrayList<Task> matchedTasks = new ArrayList<>();
        for (Task task : tasks.getTasks()) {
            if (task.isMatched(keyword)) {
                matchedTasks.add(task);
            }
        }
        return new TaskList(matchedTasks);
    }

    /**
     * Executes the command to find and display tasks that match the keyword.
     * Filters the tasks based on the keyword and shows the matching tasks via the user interface.
     *
     * @param tasks The task list containing all tasks.
     * @param ui The user interface to display the matched tasks.
     * @param storage The storage object (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMatchedTask(filterTask(tasks));
    }
}
