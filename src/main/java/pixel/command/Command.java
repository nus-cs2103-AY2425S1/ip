package pixel.command;

import pixel.PixelException;
import pixel.Storage;
import pixel.Ui;
import pixel.task.TaskList;

/**
 * The abstract base class for all commands in the Pixel application.
 */
public abstract class Command {
    private boolean isExit;

    /**
     * Constructs a Command object that can be used to execute commands.
     *
     * @param isExit a boolean indicating whether the command should exit the
     *               application.
     */
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Executes the command.
     *
     * @param taskList the TaskList object that stores the tasks.
     * @param ui       the Ui object for user interaction.
     * @param storage  the Storage object for data persistence.
     * @throws PixelException if there is an error executing the command.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws PixelException;

    /**
     * Executes the command and returns the response message.
     *
     * @param taskList the TaskList object that stores the tasks.
     * @param ui       the Ui object for user interaction.
     * @param storage  the Storage object for data persistence.
     * @return the response message.
     * @throws PixelException if there is an error executing the command.
     */

    public abstract String executeAndGetResponse(TaskList taskList, Ui ui, Storage storage)
            throws PixelException;

    /**
     * Checks if the command should exit the application.
     *
     * @return true if the command should exit, false otherwise.
     */
    public boolean isExit() {
        return this.isExit;
    }
}
