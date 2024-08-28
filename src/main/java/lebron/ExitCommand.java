package lebron;

/**
 * Represents a command to exit the application.
 * This command is responsible for displaying a goodbye message and closing the input scanner.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command by displaying a goodbye message to the user interface
     * and closing the input scanner.
     *
     * @param taskList The task list (not used in this command).
     * @param ui The user interface that will display the goodbye message.
     * @param storage The storage (not used in this command).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showGoodbyeMessage();
        ui.scanner.close();
    }

    /**
     * Indicates that this command is an exit command.
     *
     * @return {@code true}, as this command causes the application to exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }

}
