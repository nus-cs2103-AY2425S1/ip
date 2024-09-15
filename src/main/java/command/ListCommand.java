package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command that displays a list of tasks.
 * The {@code ListCommand} prints the current task list to the user interface.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command.
     * This method retrieves the string representation of the task list and
     * prints it using the user interface object.
     *
     * @param taskList The task list to be displayed.
     * @param ui       The user interface object used to print the task list.
     * @param storage  The storage object, which is not used in this command.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return taskList.toString();
    }
}
