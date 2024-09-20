package dipsy.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dipsy.exception.InvalidCommandException;
import dipsy.task.Task;
import dipsy.tasklist.TaskList;
import dipsy.ui.Ui;



/**
 * Represents a command to delete a task from the task list.
 * The command follows the format "delete &lt;task_number&gt;".
 */
public class DeleteCommand extends Command {

    /** Regular expression pattern to parse the delete command input. */
    private static final Pattern DELETE_PATTERN = Pattern.compile("^delete (\\d+)");

    /**
     * Constructs a DeleteCommand with the specified user input, task list, and UI handler.
     *
     * @param userInput The user input that triggered this command.
     * @param tasks The task list associated with this command.
     * @param ui The UI handler for interacting with the user.
     */
    public DeleteCommand(String userInput, TaskList tasks, Ui ui) {
        super(userInput, tasks, ui);
    }

    /**
     * Executes the DeleteCommand by parsing the user input and deleting the specified task from the task list.
     *
     * @return A message indicating that the task has been successfully deleted, including the details of the
     *      deleted task and the updated number of tasks remaining in the task list.
     * @throws InvalidCommandException If the command format is invalid or the task number is out of bounds.
     */
    @Override
    public String execute() throws InvalidCommandException {
        Matcher matcher = DELETE_PATTERN.matcher(userInput);

        if (!matcher.matches()) {
            throw new InvalidCommandException(InvalidCommandException.ErrorType.INVALID_DELETE_COMMAND);
        }

        int userGivenIndex = Integer.parseInt(matcher.group(1));
        int taskIndex = calculateTaskIndex(userGivenIndex);
        Task deletedTask = tasks.deleteTask(taskIndex);

        return ui.getTaskDeletedMessage(deletedTask, tasks.getSize());
    }

    /**
     * Converts the 1-based user input task index into a 0-based task index for internal task list operations.
     *
     * @param userGivenIndex The 1-based index provided by the user in the input.
     * @return The 0-based index used internally for the task list.
     * @throws InvalidCommandException If the user-given index is out of bounds (i.e., less than 1 or greater
     *      than the task list size).
     */
    private int calculateTaskIndex(int userGivenIndex) throws InvalidCommandException {
        int taskIndex = userGivenIndex - 1; // Since tasks are 0-indexed.
        if (taskIndex < 0 || taskIndex >= tasks.getSize()) {
            throw new InvalidCommandException(InvalidCommandException.ErrorType.INVALID_DELETE_INDEX);
        }
        return taskIndex;
    }
}
