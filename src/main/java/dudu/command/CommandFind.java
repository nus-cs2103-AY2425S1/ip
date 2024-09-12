package dudu.command;

import java.io.IOException;
import java.util.ArrayList;

import dudu.task.Task;
import dudu.utils.Storage;
import dudu.utils.TaskList;
import dudu.utils.UI;


/**
 * Represents a find user command into the chatbot
 */
public class CommandFind extends Command {
    private String query;

    /**
     * Constructs a find user command with the user query
     *
     * @param query User query to search the tasks' descriptions
     */
    public CommandFind(String query) {
        this.query = query;
    }

    /**
     * Filters and returns tasks that has description containing the query
     *
     * @param taskList The task list on which the command is executed
     * @param ui The user interface to interact with the user
     * @param storage The storage to save tasks
     * @return List of tasks with descriptions containing the query
     */
    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) {
        ArrayList<Task> filteredTasks = taskList.findTasks(this.query.split(" "));
        return ui.findTasks(filteredTasks);
    }
}
