package GPT;
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
        this.index = Integer.parseInt(input.split(" ")[1]) - 1;
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
        try {
            taskList.markTask(index);
            storage.saveTasks(taskList.getTasks());
            ui.showTaskAdded(taskList.getTasks().get(index), taskList.getTasks().size());
        } catch (GPTException e) {
            ui.showError(e.getMessage());
        }
    }
}