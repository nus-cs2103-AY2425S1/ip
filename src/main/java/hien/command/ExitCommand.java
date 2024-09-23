package hien.command;

import hien.exception.HienException;
import hien.main.Storage;
import hien.main.TaskList;
import hien.ui.UI;

/**
 * Represents a command to exit the application.
 * When executed, this command will display a goodbye message to the user and signal the application to terminate.
 */
public class ExitCommand extends Command {

    /**
     * Constructs an ExitCommand object.
     *
     * @param isExit Specifies whether this command will terminate the application.
     */
    public ExitCommand(boolean isExit) {
        super(isExit);
    }

    /**
     * Executes the command to exit the application.
     * Displays a goodbye message using the UI and returns it as a string.
     *
     * @param tasks   The task list (unused in this command).
     * @param ui      The UI object that interacts with the user.
     * @param storage The storage object (unused in this command).
     * @return        A string containing the goodbye message.
     * @throws HienException This command does not throw any exceptions.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws HienException {
        return ui.showGoodbye();
    }
}
