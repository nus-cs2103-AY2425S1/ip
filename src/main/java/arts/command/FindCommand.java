package arts.command;

import arts.ArtsException;
import arts.task.TaskList;
import arts.util.Storage;
import arts.util.Ui;

/**
 * Represents a command to find tasks containing a specific keyword.
 */
public class FindCommand implements Command {
    private final TaskList tasks;
    private final Ui ui;
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified task list, UI, and keyword.
     *
     * @param tasks   The list of tasks to search within.
     * @param ui      The user interface to display results.
     * @param keyword The keyword to search for in tasks.
     */
    public FindCommand(TaskList tasks, Ui ui, String keyword) {
        this.tasks = tasks;
        this.ui = ui;
        this.keyword = keyword;
    }

    /**
     * Executes the find command, searching for tasks that contain the keyword
     * and displaying them to the user.
     *
     * @throws ArtsException If an error occurs during execution.
     */
    @Override
    public void execute() throws ArtsException {
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        int count = 0;
        for (int i = 0; i < tasks.size(); i++) {
            String task = tasks.getTask(i).toString();
            if (task.contains(keyword)) {
                sb.append(++count).append(". ").append(task).append("\n");
            }
        }
        if (count == 0) {
            ui.showMessage("No matching tasks found.");
        } else {
            ui.showMessage(sb.toString());
        }
    }
}

