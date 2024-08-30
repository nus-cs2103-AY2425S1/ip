package nugget;

/**
 * Represents a command that lists all tasks in the task list.
 */
public class ListCommand implements Command {

    /**
     * Executes the list command, displaying all tasks in the task list.
     *
     * @param tasks The list of tasks to be displayed.
     * @param ui The user interface that displays messages to the user.
     * @param storage The storage that handles the saving of tasks.
     * @throws NuggetException If an error occurs during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NuggetException {
        int len = tasks.size();
        ui.line();
        for (int i = 0; i < len; i++) {
            String formattedMessage = String.format("%d.%s", i + 1, tasks.getTask(i));
            System.out.println(formattedMessage);
        }
        ui.line();
    }
}
