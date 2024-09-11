package ollie.command;

import ollie.Response;
import ollie.exception.OllieException;
import ollie.Storage;
import ollie.TaskList;
import ollie.Ui;

/**
 * Represents a command for ending the conversation.
 */
public class ExitCommand extends Command {

    /**
     * Execute the command to save the data and exit the conversation.
     *
     * @param tasks   List of tasks.
     * @param ui      User interface controller.
     * @param storage Storage controller for file manipulation.
     */
    @Override
    public Response execute(TaskList tasks, Ui ui, Storage storage) throws OllieException {
        storage.save(tasks.getTasks());
        return new Response(ui.getExitMessage(), true);
    }

    ;
}
