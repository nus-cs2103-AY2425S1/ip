package Buu;

/**
 * Represents a command to unmark a task as not completed while preserving its priority.
 * The task index is extracted from the user input, and if the format is invalid,
 * a custom {@link TaskException} is thrown.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs an UnmarkCommand by parsing the user input to determine the index of the task to be unmarked.
     * If the input format is invalid or the task number is not an integer, a {@link TaskException} is thrown with
     * a meaningful message, providing the correct usage format.
     *
     * @param input The user input string containing the command to unmark a task.
     * @throws TaskException if the input format is invalid or the task number is not an integer.
     */
    public UnmarkCommand(String input) throws TaskException {
        // Precondition: Ensure the input is not null
        assert input != null : "Input should not be null";

        String[] parts = input.trim().split(" ");

        // Validate the input format
        if (parts.length != 2) {
            throw new TaskException("Invalid format for 'unmark' command.\n"
                    + "Correct format: unmark <task number>\n"
                    + "Example: unmark 1");
        }

        // Parse the task index and handle invalid number format
        try {
            this.index = Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new TaskException("Task number must be a valid integer.\n"
                    + "Correct format: unmark <task number>\n"
                    + "Example: unmark 1");
        }

        // Postcondition: Ensure that the index is valid
        if (index < 0) {
            throw new TaskException("Task index should be a positive integer.\n"
                    + "Correct format: unmark <task number>\n"
                    + "Example: unmark 1");
        }
    }

    /**
     * Executes the command to unmark the specified task while preserving its priority.
     * If the task number is out of range, an appropriate {@link TaskException} is thrown.
     *
     * @param taskList The list of tasks containing the task to be unmarked.
     * @param ui       The user interface that displays messages to the user.
     * @param storage  The storage handler that manages task persistence.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            // Unmark the task and save the updated task list
            taskList.unmarkTask(index);
            storage.saveTasks(taskList.getTasks());
            ui.showTaskUnmarked(taskList.getTasks().get(index)); // Display the unmarked task
        } catch (TaskException e) {
            ui.showError(e.getMessage()); // Display error message if task unmarking fails
        }
    }
}
