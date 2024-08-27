package GPT;
/**
 * Represents a command to unmark a task as not done in the GPT application.
 * This command identifies the task to be unmarked based on the user input and updates its status.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs an UnmarkCommand by parsing the user input to determine the index of the task to be unmarked.
     * The index is adjusted to be zero-based (subtracting 1).
     *
     * @param input The user input string containing the command to unmark a task as not done.
     */
    public UnmarkCommand(String input) {
        this.index = Integer.parseInt(input.split(" ")[1]) - 1;
    }

    /**
     * Executes the command to unmark the specified task as not done. If the task index is valid,
     * the task is unmarked, the updated list is saved, and a confirmation message is shown.
     * If the index is invalid, an error message is displayed.
     *
     * @param taskList The list of tasks containing the task to be unmarked.
     * @param ui       The user interface that displays messages to the user.
     * @param storage  The storage handler that manages task persistence.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.unmarkTask(index);
            storage.saveTasks(taskList.getTasks());
            ui.showTaskAdded(taskList.getTasks().get(index), taskList.getTasks().size());
        } catch (GPTException e) {
            ui.showError(e.getMessage());
        }
    }
}
