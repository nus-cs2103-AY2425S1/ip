package Buu;
/**
 * Represents a command to mark a task as completed in the GPT application.
 * This command identifies the task to be marked based on the user input and updates its status.
 */
public class MarkCommand extends Command {
    private int index;
    /**
     * Constructs a MarkCommand by parsing the user input to determine the index of the task to be marked.
     * The index is adjusted to be zero-based (subtracting 1).
     *
     * @param input The user input string containing the command to mark a task as completed.
     */
    public MarkCommand(String input) {
        // Precondition: Ensure the input is not null and has the right format
        assert input != null : "Input should not be null";
        assert input.split(" ").length > 1 : "Invalid input format for mark command";

        this.index = Integer.parseInt(input.split(" ")[1]) - 1;

        // Postcondition: Ensure that the index is not negative
        assert index >= 0 : "Task index should be non-negative";
    }

    /**
     * Executes the command to mark the specified task as completed. If the task index is valid,
     * the task is marked as completed, the updated list is saved, and a confirmation message is shown.
     * If the index is invalid, an error message is displayed.
     *
     * @param taskList The list of tasks containing the task to be marked.
     * @param ui       The user interface that displays messages to the user.
     * @param storage  The storage handler that manages task persistence.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        // Preconditions: Ensure taskList, ui, and storage are not null
        assert taskList != null : "TaskList should not be null";
        assert ui != null : "UI should not be null";
        assert storage != null : "Storage should not be null";

        // Internal Invariant: Ensure index is within bounds of taskList
        assert index < taskList.getTasks().size() : "Task index is out of bounds";
        try {
            taskList.markTask(index);
            storage.saveTasks(taskList.getTasks());
            // Show confirmation message that the task is marked as done
            ui.showTaskMarkedDone(taskList.getTasks().get(index));
        } catch (GPTException e) {
            ui.showError(e.getMessage());
        }
    }
}
