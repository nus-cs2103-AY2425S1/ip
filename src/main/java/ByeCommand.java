/**
 * Represents the command that terminates the application.
 * This command will display a goodbye message to the user and signal that the program should exit.
 *
 * @author Aaron
 */
public class ByeCommand extends Command {
    /**
     * Executes the ByeCommand by displaying a goodbye message to the user.
     *
     * @param tasks The task list, which remains unchanged by this command.
     * @param ui The Ui instance that will display the goodbye message.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        Ui.goodbye();
    }

    /**
     * Indicates that this command is a goodbye command.
     *
     * @return true, as this command is meant to exit the application.
     */
    @Override
    public boolean isGoodbye() {
        return true;
    }
}
