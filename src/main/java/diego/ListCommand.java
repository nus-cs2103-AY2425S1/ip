package diego;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand implements Command {

    /**
     * Executes the command to list all tasks in the task list.
     *
     * @param tasks   The task list to be displayed.
     * @param ui      The UI component that handles user interaction.
     * @param storage The storage component (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
