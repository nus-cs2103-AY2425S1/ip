package gallium.command;

import gallium.main.GalliumException;
import gallium.main.Storage;
import gallium.main.TaskList;
import gallium.main.Ui;

/**
 * Represents a command to exit the program.
 * This command prints a goodbye message and saves the task list.
 */
public class ErrorCommand extends Command {
    /**
     * Executes the bye command, prints a goodbye message and saves the task list.
     *
     * @param tasklist The list of tasks to execute the command on.
     * @param ui       The user interface that will interact with user.
     * @param storage  The storage that will save any changes made by the command.
     * @throws GalliumException If an error occurs during the execution of the
     *                          command.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws GalliumException {
        ui.printInputAgainMessage();
    }

}
