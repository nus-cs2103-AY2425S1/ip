/**
 * Represents a command to exit the program.
 * This command implements the Command interface and exits the program when
 * the specified keyword is entered and displays the termination message to
 * the user.
 */
public class ExitCommand implements Command {

    /**
     * @inheritDoc
     *
     * Constructs a ExitCommand, which executes the exit command by displaying
     * the termination message to the user.
     *
     * @param tasks The TaskList, which is not used in this command but required by
     *              the interface.
     * @param ui The Ui object used to display the termination message.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.displayGoodbye();
    }

    /**
     * @inheritDoc
     *
     * @return true to indicate that the program should terminate
     * after this command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
