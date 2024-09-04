package gavinchatbot.command;

import java.util.ArrayList;

import gavinchatbot.task.Task;
import gavinchatbot.task.TaskList;
import gavinchatbot.util.GavinException;
import gavinchatbot.util.Storage;
import gavinchatbot.util.Ui;

/**
 * Represents a command to find tasks containing a specific keyword in their description.
 */
public class FindCommand implements Command {
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
     * Executes the find command, searching for tasks in the TaskList that contain the keyword,
     * and displaying the found tasks through the UI.
     *
     * @param tasks   The list of tasks to search through.
     * @param ui      The UI object used to display the found tasks.
     * @param storage The Storage object used for saving/loading tasks (not used in this command).
     * @return A message listing the found tasks.
     * @throws GavinException If an error occurs while executing the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws GavinException {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i);
            if (task.getDescription().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return ui.showFoundTasks(foundTasks);
    }

    /**
     * Indicates whether this command should cause the application to exit.
     *
     * @return false, as this command does not cause the application to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
