package cookie.command;

import cookie.storage.Storage;
import cookie.task.TaskList;
import cookie.ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the {@code ListCommand} by retrieving all tasks from the {@code TaskList} and
     * generating a string representation of the entire task list.
     *
     * @param taskList the list of tasks to be retrieved and displayed
     * @param ui the user interface object used to format and print the list of tasks
     * @param storage the storage object (not used in this method, but included for command consistency)
     * @return a string containing the list of all tasks in the task list
     */
    @Override
    public String executeCommand(TaskList taskList, Ui ui, Storage storage) {
        String response = "Here are all tasks in your list!\n";
        response = response + ui.printTasks(taskList.getTaskArrayList());
        return response;
    }
}
