package pixel.command;

import pixel.PixelException;
import pixel.Storage;
import pixel.Ui;
import pixel.task.TaskList;

/**
 * Represents a command to exit the application. When executed, it displays a
 * farewell message and closes the user interface.
 */
public class ExitCommand extends Command {

    /**
     * Constructs an ExitCommand object.
     */
    public ExitCommand() {
        super(true);
    }

    /**
     * Executes the exit command. Displays a farewell message and closes the user
     * interface.
     *
     * @param taskList The task list object.
     * @param ui       The user interface object.
     * @param storage  The storage object.
     * @throws PixelException If there is an error executing the command.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws PixelException {
        ui.pixelSays("Bye. Hope to see you again soon!");
        ui.closeUi();
    }

    /**
     * Executes the exit command and returns the response message.
     *
     * @param taskList The task list object.
     * @param ui       The user interface object.
     * @param storage  The storage object.
     * @return The response message.
     * @throws PixelException If there is an error executing the command.
     */
    @Override
    public String executeAndGetResponse(TaskList taskList, Ui ui, Storage storage) throws PixelException {
        return "Bye. Hope to see you again soon!";
    }
}
