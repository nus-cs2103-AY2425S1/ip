package mylo.command;

import mylo.data.InsufficientInfoException;
import mylo.storage.StorageOperationException;
import mylo.task.TaskList;
import mylo.task.TaskType;
import mylo.ui.Tui;
import mylo.utils.exceptions.IllegalValueException;

/**
 * Represents an add command that adds a new task to the task list.
 * The task to be added is specified by its type and details.
 * <p></p>
 * <p>This class is responsible for executing the command to add a task to the
 * task list, displaying the result to the user via the Tui.
 * </p>
 *
 * @author cweijin
 */
public class AddCommand extends Command {
    private final TaskType TYPE;
    private final String DETAILS;

    /**
     * Constructs an {@code AddCommand} object with the specified task type and details.
     *
     * @param type    The type of the task to be added (e.g., TODO, DEADLINE, EVENT).
     * @param details The details of the task to be added, such as description or due date.
     */
    public AddCommand(TaskType type, String details) {
        this.TYPE = type;
        this.DETAILS = details;
    }

    /**
     * Executes the add command, adding a task to the given task list and displaying the result.
     *
     * @param list The task list where the task will be added.
     * @param tui  The user interface that shows the result of the command execution.
     * @throws StorageOperationException If there is an issue with saving the task to storage.
     * @throws InsufficientInfoException If the task details provided are incomplete or invalid.
     * @throws IllegalValueException     If the date time provided is not in correct format.
     */
    @Override
    public String execute(TaskList list, Tui tui) throws StorageOperationException, InsufficientInfoException,
            IllegalValueException {
        return list.addTask(DETAILS, TYPE);
    }
}
