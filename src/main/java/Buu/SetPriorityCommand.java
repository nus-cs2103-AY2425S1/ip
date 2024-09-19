package Buu;

/**
 * Represents a command to set the priority of a task.
 * This command ensures the proper format is used when setting a task's priority.
 * If the format is incorrect or the priority is invalid, the appropriate error message is displayed to the user.
 */
public class SetPriorityCommand extends Command {
    private int index;
    private int priority;

    /**
     * Constructs a SetPriorityCommand by parsing the user input to determine the task index and priority.
     *
     * @param input The user input string containing the command to set priority for a task.
     */
    public SetPriorityCommand(String input) {
        // Split the input for parsing
        String[] parts = input.split(" ");

        // Default to invalid values
        this.index = -1;
        this.priority = -1;

        // Validate the input format
        if (parts.length != 3) {
            // Error message will be handled in the execute method
            return;
        }

        try {
            // Parse the task index and priority
            this.index = Integer.parseInt(parts[1]) - 1; // Adjust to zero-based index
            this.priority = Integer.parseInt(parts[2]);
        } catch (NumberFormatException e) {
            // Error message will be handled in the execute method
            return;
        }

        // Ensure that the index and priority are within valid ranges
        if (index < 0 || priority < 1 || priority > 3) {
            // Error message will be handled in the execute method
            return;
        }
    }

    /**
     * Executes the command to set the priority of the specified task.
     *
     * @param taskList The list of tasks containing the task to set priority for.
     * @param ui       The user interface that displays messages to the user.
     * @param storage  The storage handler that manages task persistence.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        // Ensure the task index is within bounds before proceeding
        if (index < 0 || index >= taskList.getTasks().size()) {
            ui.showError("Task index out of bounds. No task exists at the specified index.\n"
                    + "Usage: priority <task number> <priority>\n"
                    + "Example: priority 1 2 (for Medium Priority)");
            return;
        }

        // Ensure that the priority is within valid ranges
        if (priority < 1 || priority > 3) {
            ui.showError("Priority should be between 1 (Low) and 3 (High).\n"
                    + "Usage: priority <task number> <priority>\n"
                    + "Example: priority 1 2 (for Medium Priority)");
            return;
        }

        // Set the task priority
        try {
            taskList.setTaskPriority(index, priority);
            storage.saveTasks(taskList.getTasks());
            ui.showTaskPrioritySet(taskList.getTasks().get(index));
        } catch (TaskException e) {
            ui.showError(e.getMessage());
        }
    }
}
