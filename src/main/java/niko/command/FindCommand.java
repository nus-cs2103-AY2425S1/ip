package niko.command;

import java.util.ArrayList;

import niko.common.NikoException;
import niko.main.Storage;
import niko.main.Ui;
import niko.task.Task;
import niko.task.TaskList;


/**
 * Represents a command to find tasks in the task list that match a given description.
 */
public class FindCommand extends Command {
    private String description;

    /**
     * Constructs a FindCommand object.
     *
     * @param description The description to search for in the tasks.
     */
    public FindCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command to find tasks matching the description in the task list.
     * If no matching tasks are found, a message is displayed to the user.
     * If an error occurs during the search, an error message is displayed.
     *
     * @param tasks   The task list to search within.
     * @param ui      The user interface to display messages.
     * @param storage The storage to save data if needed.
     * @return
     * @throws NikoException If an error occurs during the command execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws NikoException {
        String response;
        try {
            ArrayList<Task> taskList = tasks.findTasks(this.description);

            if (taskList == null || taskList.isEmpty()) {
                response = ui.showNoMatchingTasksMessage();
            } else {
                response = ui.showTaskList(taskList);
            }

        } catch (Exception e) {

            response = ui.showErrorMessage("An error occurred while finding tasks: " + e.getMessage());
        }
        return response;
    }
}
