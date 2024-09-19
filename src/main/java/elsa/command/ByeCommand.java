package elsa.command;

import elsa.ElsaException;
import elsa.Storage;
import elsa.task.TaskList;
import elsa.ui.Ui;

/**
 * Represents the command that terminates the application.
 * This command will display a goodbye message to the user and signal that the program should exit.
 *
 * @author Aaron
 */
public class ByeCommand extends Command {
    /**
     * Executes the elsa.command.ByeCommand by displaying a goodbye message to the user.
     *
     * @param tasks The task list, which remains unchanged by this command.
     * @param ui The elsa.ui.Ui instance that will display the goodbye message.
     * @return A response string representing the result of the command execution, which can be displayed to the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) throws ElsaException {
        // Save all tasks to the Elsa.txt data file when the user says "bye".
        Storage storage = new Storage();
        storage.saveTasksToDataFile(tasks);

        return Ui.goodbye();
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
