package ollie.command;

import ollie.History;
import ollie.Response;
import ollie.Storage;
import ollie.TaskList;
import ollie.Ui;

/**
 * Represents a command to display the list of tasks.
 */
public class ListCommand extends Command {

    /**
     * Execute the call for Ui to print the list of tasks.
     *
     * @param tasks   List of tasks.
     * @param ui      User interface controller.
     * @param storage Storage controller for file manipulation.
     */
    @Override
    public Response execute(TaskList tasks, Ui ui, Storage storage, History history) {
        history.add(new EmptyCommand());
        return new Response(ui.getTaskListMessage(tasks), false);
    }
}
