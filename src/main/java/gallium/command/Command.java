package gallium.command;

import gallium.main.GalliumException;
import gallium.main.Storage;
import gallium.main.TaskList;
import gallium.main.Ui;

/**
 * Represents an abstract command that can be executed.
 * Subclasses should implement the execute method for the command.
 */
public abstract class Command {
    /**
     * Executes the command with the given taskList, Ui and storage.
     * 
     * @param tasklist The list of tasks to execute the command on.
     * @param ui       The user interface that will interact with user.
     * @param storage  The storage that will save any changes made by the command.
     * @throws GalliumException If an error occurs during the execution of the
     *                          command.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws GalliumException {

    }

    /**
     * Determintes whether the command will exit the program.
     * 
     * @return {@code true} if the command will exit the program.
     */

    public boolean isExit() {
        return false;
    }
}
