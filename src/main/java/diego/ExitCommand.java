package diego;

/**
 * Represents a command to exit the Diego application.
 */
public class ExitCommand implements Command {

    /**
     * Executes the command to exit the Diego application.
     *
     * @param tasks   The task list (not used in this command).
     * @param ui      The UI component that handles user interaction.
     * @param storage The storage component (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbyeMessage();
        System.exit(0);
    }
}
