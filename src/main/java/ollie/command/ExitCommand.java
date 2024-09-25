package ollie.command;

import ollie.History;
import ollie.Response;
import ollie.Storage;
import ollie.TaskList;
import ollie.Ui;
import ollie.exception.OllieException;

/**
 * Represents a command for ending the conversation.
 */
public class ExitCommand extends Command {

    /**
     * Executes the command to save the data and exit the conversation.
     *
     * @param tasks   List of tasks.
     * @param ui      User interface controller.
     * @param storage Storage controller for file manipulation.
     */
    @Override
    public Response execute(TaskList tasks, Ui ui, Storage storage, History history) throws OllieException {
        storage.save(tasks.getTasks());
        return new Response(ui.getExitMessage(), true);
    }
}
