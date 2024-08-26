package friday.command;

import friday.Storage;
import friday.TaskList;
import friday.Ui;

/**
 * Represents a command to list all tasks in the task list.
 * Inherits from the Command class and provides functionality to display all tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the command to display all tasks in the task list.
     * This method calls the Ui object to show the list of tasks.
     *
     * @param tasks  The TaskList containing all tasks to be displayed.
     * @param ui     The Ui object for displaying the list of tasks to the user.
     * @param storage The Storage object is not used in this command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }

    /**
     * Indicates whether the command is an exit command.
     *
     * @return false as this command does not terminate the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}