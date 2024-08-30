package chatbaby;

/**
 * Represents a command to exit the ChatBaby application.
 * Extends the Command class to implement the functionality for exiting the application.
 */
public class ExitCommand extends Command {

    /**
     * Constructs an ExitCommand with the specified command body.
     *
     * @param commandBody The body of the command, which is unused for the exit command.
     */
    public ExitCommand(String commandBody) {
        super(commandBody);
    }

    /**
     * Executes the exit command, which triggers the application's exit process.
     * This method displays a goodbye message to the user.
     *
     * @param tasks The list of tasks in the application.
     * @param ui The user interface for displaying messages.
     * @param storage The storage object for saving and loading tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.bye();
    }

    /**
     * Indicates that this command results in the application exiting.
     *
     * @return true, as this command causes the application to exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
