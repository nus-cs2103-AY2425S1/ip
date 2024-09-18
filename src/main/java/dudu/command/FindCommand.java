package dudu.command;

import java.util.ArrayList;

import dudu.task.Task;
import dudu.utils.Storage;
import dudu.utils.TaskList;
import dudu.utils.UI;

/**
 * Represents command to find matching tasks using descriptions.
 */
public class FindCommand extends Command {
    private String query;

    /**
     * Constructs a FindCommand.
     *
     * @param query User query to search the tasks' descriptions
     */
    public FindCommand(String query) {
        this.query = query;
    }

    /**
     * Returns tasks with description containing the query.
     *
     * @param taskList Task list containing the tasks.
     * @param ui User interface to interact with the user.
     * @param storage Storage to save tasks.
     */
    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) {
        ArrayList<Task> filteredTasks = taskList.findTasks(this.query.split(" "));
        return ui.getFindTasksMessage(filteredTasks);
    }
}
