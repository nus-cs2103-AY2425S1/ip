package Buu;

/**
 * Represents a command to mark a task as completed without setting its priority.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructs a MarkCommand by parsing the user input to determine the index of the task to be marked.
     *
     * @param input The user input string containing the command to mark a task.
     */
    public MarkCommand(String input) {
        String[] parts = input.trim().split(" ");

        // Default to an invalid index
        this.index = -1;

        if (parts.length != 2) {
            // Error will be handled in the execute method
            return;
        }

        try {
            this.index = Integer.parseInt(parts[1]) - 1; // Adjust to zero-based index
        } catch (NumberFormatException e) {
            // Invalid number will be handled in the execute method
            this.index = -1; // Set to invalid index
        }
    }

    /**
     * Executes the command to mark the specified task as completed.
     *
     * @param taskList The list of tasks containing the task to be marked.
     * @param ui       The user interface that displays messages to the user.
     * @param storage  The storage handler that manages task persistence.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        // Validate the index
        if (index < 0 || index >= taskList.getTasks().size()) {
            ui.showError("Invalid task index. Please enter a valid index.\n"
                    + "Usage: mark <task index>\n"
                    + "Example: mark 1\n");
            return;
        }

        try {
            taskList.markTask(index); // Mark the task as done
            storage.saveTasks(taskList.getTasks()); // Save updated task list
            ui.showTaskMarkedDone(taskList.getTasks().get(index)); // Display the marked task
        } catch (TaskException e) {
            ui.showError(e.getMessage()); // Display error message if marking fails
        }
    }
}
