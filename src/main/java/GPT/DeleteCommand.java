package GPT;
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
        this.index = Integer.parseInt(input.split(" ")[1]) - 1;
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
        try {
            Task removedTask = taskList.getTasks().get(index);
            taskList.deleteTask(index);
            storage.saveTasks(taskList.getTasks());
            ui.showTaskRemoved(removedTask, taskList.getTasks().size());
        } catch (GPTException e) {
            ui.showError(e.getMessage());
        }
    }
}
