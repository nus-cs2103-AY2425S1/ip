package charlotte.command;

import charlotte.exception.CharlotteException;
import charlotte.storage.Storage;
import charlotte.task.Task;
import charlotte.task.TaskList;
import charlotte.ui.Ui;

/**
 * Represents a command to find tasks by a given keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching for tasks that contain the keyword.
     *
     * @param tasks The list of tasks to search through.
     * @param ui The user interface to display the results.
     * @param storage The storage to save the tasks (not used in this command).
     * @throws CharlotteException If an error occurs during the execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws CharlotteException {
        TaskList matchingTasks = new TaskList();
        StringBuilder result = new StringBuilder();

        //find matching tasks
        for (Task task : tasks.getTasks()) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.addTask(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            result.append(ui.showMessage("No tasks found matching the keyword: " + keyword));
        } else {
            result.append(ui.printLine()).append("\n");
            result.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchingTasks.getSize(); i++) {
                result.append((i + 1)).append(". ").append(matchingTasks.getTask(i)).append("\n");
            }
            result.append(ui.printLine()).append("\n");
        }

        return result.toString();
    }
}
