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
     * @throws TaskException if the input format is invalid.
     */
    public MarkCommand(String input) throws TaskException {
        // Precondition: Ensure the input is not null
        assert input != null : "Input should not be null";
        String[] parts = input.trim().split(" ");

        if (parts.length != 2) {
            throw new TaskException("Invalid format for 'mark' command.\n"
                    + "Correct format: mark <task number>\nExample: mark 1");
        }

        try {
            this.index = Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new TaskException("Task number must be a valid integer.\n"
                    + "Correct format: mark <task number>\nExample: mark 1");
        }

        // Postcondition: Ensure that the index is valid
        assert index >= 0 : "Task index should be non-negative";
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
        try {
            taskList.markTask(index); // Mark the task as done without affecting the priority
            storage.saveTasks(taskList.getTasks());
            ui.showTaskMarkedDone(taskList.getTasks().get(index));
        } catch (TaskException e) {
            ui.showError(e.getMessage());
        }
    }
}
