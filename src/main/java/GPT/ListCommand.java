package GPT;
/**
 * Represents a command to list all tasks in the GPT application.
 * This command displays the current list of tasks to the user.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command by displaying the current list of tasks.
     *
     * @param taskList The list of tasks to be displayed.
     * @param ui       The user interface that displays messages to the user.
     * @param storage  The storage handler that manages task persistence (not used in this command).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showTaskList(taskList.getTasks());
    }
}