package ollie.command;

import ollie.History;
import ollie.Response;
import ollie.Storage;
import ollie.TaskList;
import ollie.Ui;
import ollie.exception.OllieException;

/**
 * Represents a command for undoing the previous command
 */
public class EmptyCommand extends Command {

    /**
     * Execute the undoing of a display task
     *
     * @param tasks   List of tasks.
     * @param ui      User interface controller.
     * @param storage Storage controller for file manipulation.
     */
    @Override
    public Response execute(TaskList tasks, Ui ui, Storage storage, History history) throws OllieException {
        return new Response(ui.getEmptyMessage(), false);
    }
}
