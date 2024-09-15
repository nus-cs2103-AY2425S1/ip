package simon;
/**
 * Represents a command to list all tasks in the task list.
 * Implements the Command interface to define the execution behavior for displaying the task list.
 */
public class ListCommand implements Command {
    /**
     * Constructs a ListCommand. This command does not require any additional parameters.
     */
    public ListCommand() {
    }
    /**
     * Executes the command to display the list of all tasks.
     * The task list is shown to the user through the user interface.
     * This command does not modify the task list or the storage.
     *
     * @param taskList the list of tasks to be displayed
     * @param ui the user interface used to show the task list to the user
     * @param storage the storage used to save or retrieve data (not used in this command)
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showTaskList(taskList);
    }
}
