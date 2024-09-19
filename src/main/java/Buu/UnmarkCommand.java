package Buu;

/**
 * Represents a command to unmark a task as not completed while preserving its priority.
 * The task index is extracted from the user input, and if the format is invalid,
 * a user-friendly message is displayed.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs an UnmarkCommand by parsing the user input to determine the index of the task to be unmarked.
     *
     * @param input The user input string containing the command to unmark a task.
     */
    public UnmarkCommand(String input) {
        // Split the input for parsing
        String[] parts = input.trim().split(" ");

        // Default to an invalid index
        this.index = -1;

        // Validate the input format
        if (parts.length != 2) {
            // Format error will be handled in the execute method
            return;
        }

        try {
            // Parse the task index
            this.index = Integer.parseInt(parts[1]) - 1; // Adjust to zero-based index
        } catch (NumberFormatException e) {
            // Invalid number will be handled in the execute method
            this.index = -1; // Set to invalid index
        }

        // No need for additional validation here; it's handled in execute
    }

    /**
     * Executes the command to unmark the specified task while preserving its priority.
     * If the task index is out of range, an appropriate error message is displayed.
     *
     * @param taskList The list of tasks containing the task to be unmarked.
     * @param ui       The user interface that displays messages to the user.
     * @param storage  The storage handler that manages task persistence.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        // Validate the index
        if (index < 0 || index >= taskList.getTasks().size()) {
            ui.showError("Invalid task index. Please enter a valid index.\n"
                    + "Usage: unmark <task index>\n"
                    + "Example: unmark 1\n");
            return;
        }

        try {
            taskList.unmarkTask(index); // Unmark the task
            storage.saveTasks(taskList.getTasks()); // Save updated task list
            ui.showTaskUnmarked(taskList.getTasks().get(index)); // Display the unmarked task
        } catch (TaskException e) {
            ui.showError(e.getMessage()); // Display error message if unmarking fails
        }
    }
}
