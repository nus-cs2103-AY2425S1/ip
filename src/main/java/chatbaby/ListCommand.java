package chatbaby;

/**
 * Represents a command to list all tasks in the ChatBaby application.
 * Extends the Command class to implement the functionality for listing tasks.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand with the specified command body.
     *
     * @param commandBody The body of the command, which is unused for the list command.
     */
    public ListCommand(String commandBody) {
        super(commandBody);
    }

    /**
     * Executes the list command, which displays all the tasks in the task list.
     *
     * @param tasks The list of tasks in the application.
     * @param ui The user interface for displaying messages.
     * @param storage The storage object for saving and loading tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "Task list should not be null";
        tasks.listTasks();
    }
}
