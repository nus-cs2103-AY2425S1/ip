package Buu;
/**
 * Represents a command to delete a task from the task list in the GPT application.
 * This command parses the user input to determine which task to delete by its index.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a DeleteCommand by parsing the user input to extract the index of the task
     * to be deleted. The index is adjusted to be zero-based (subtracting 1).
     *
     * @param input The user input string containing the command to delete a task.
     */
    public DeleteCommand(String input) {
        // Preconditions: Ensure input is valid and index can be parsed
        assert input != null : "Input cannot be null";
        assert input.split(" ").length > 1 : "Input should contain a task index";

        this.index = Integer.parseInt(input.split(" ")[1]) - 1;
        // Postcondition: Ensure index is not negative
        assert this.index >= 0 : "Index should not be negative";
    }

    /**
     * Executes the command to delete the specified task from the task list. If the task
     * index is valid, the task is removed, the updated list is saved, and a confirmation
     * message is shown. If the index is invalid, an error message is displayed.
     *
     * @param taskList The list of tasks from which the task will be deleted.
     * @param ui       The user interface that displays messages to the user.
     * @param storage  The storage handler that manages task persistence.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        // Preconditions: Ensure taskList, ui, and storage are not null
        assert taskList != null : "TaskList should not be null";
        assert ui != null : "UI should not be null";
        assert storage != null : "Storage should not be null";

        try {
            assert index < taskList.getTasks().size() : "Index out of bounds";
            Task removedTask = taskList.getTasks().get(index);
            taskList.deleteTask(index);
            storage.saveTasks(taskList.getTasks());
            ui.showTaskRemoved(removedTask, taskList.getTasks().size());
        } catch (TaskException Exception) {
            ui.showError(Exception.getMessage());
        }
    }
}
