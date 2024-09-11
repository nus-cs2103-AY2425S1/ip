package duke.commands;

import duke.exceptions.InvalidInputException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to list all tasks in the DailyTasks application.
 * This command retrieves and displays the entire list of tasks.
 */
public class ListTaskCommand extends Command {

    /**
     * Constructs a new ListTaskCommand.
     * This command does not require any parameters.
     */
    public ListTaskCommand() {
        super();
    }

    /**
     * Executes the command by displaying the entire list of tasks.
     * The task list is formatted and displayed using the user interface.
     *
     * @param taskList The list of tasks to be displayed.
     * @param ui The user interface used to display the task list.
     * @param storage The storage system responsible for saving and loading tasks (not used in this implementation).
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidInputException {
        // Assert that taskList and ui are not null before proceeding
        assert taskList != null : "Task list must not be null";
        assert ui != null : "UI object must not be null";

        // Ensure task list is fetched and passed to the UI
        return ui.formatTaskListings(taskList.getTasks(), false);
    }
}
