package GPT;
/**
 * Represents a command to exit the GPT application.
 * This command displays a goodbye message and signals the application to terminate.
 */
public class ExitCommand extends Command {
    /**
     * Executes the command to exit the application by displaying a goodbye message.
     *
     * @param taskList The list of tasks (not used in this command).
     * @param ui       The user interface that displays messages to the user.
     * @param storage  The storage handler that manages task persistence (not used in this command).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showGoodbyeMessage();
    }

    /**
     * Indicates that this command will exit the program.
     *
     * @return true, indicating that the program should terminate after this command is executed.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}