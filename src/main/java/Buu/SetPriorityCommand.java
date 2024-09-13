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
     * @throws IllegalArgumentException If the command format is
     *     invalid or if the index or priority is not within the valid range
     */
    public SetPriorityCommand(String input) {
        // Precondition: Ensure the input is not null
        assert input != null : "Input should not be null";
        String[] parts = input.split(" ");

        // Validate the input format and provide meaningful feedback to the user if it's incorrect
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid command format for setting priority.\n"
                    + "Usage: priority <task number> <priority>\n"
                    + "Example: priority 1 2 (for Medium Priority)");
        }

        try {
            // Parse the task index and priority, which must both be integers
            this.index = Integer.parseInt(parts[1]) - 1;
            this.priority = Integer.parseInt(parts[2]);
        } catch (NumberFormatException e) {
            // Handle invalid number inputs and provide usage feedback
            throw new IllegalArgumentException("Invalid task number or priority. Both must be integers.\n"
                    + "Usage: priority <task number> <priority>\n"
                    + "Example: priority 1 2 (for Medium Priority)");
        }

        // Postcondition: Ensure that the index and priority are within valid ranges
        if (index < 0 || priority < 1 || priority > 3) {
            throw new IllegalArgumentException("Priority should be between 1 (Low) and 3 (High).\n"
                    + "Usage: priority <task number> <priority>\n"
                    + "Example: priority 1 2 (for Medium Priority)");
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
        try {
            // Set the priority of the task at the specified index
            taskList.setTaskPriority(index, priority);
            // Save the updated tasks to storage
            storage.saveTasks(taskList.getTasks());
            // Display confirmation to the user with the updated task
            ui.showTaskPrioritySet(taskList.getTasks().get(index));
        } catch (TaskException e) {
            // Catch and display any application-specific exceptions
            ui.showError(e.getMessage());
        } catch (IllegalArgumentException e) {
            // Catch and display standard input-related exceptions
            ui.showError(e.getMessage());
        }
    }
}
