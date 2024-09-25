package ollie.command;

import ollie.History;
import ollie.Response;
import ollie.Storage;
import ollie.TaskList;
import ollie.Ui;
import ollie.exception.OllieException;

/**
 * Represents a command for finding a particular task.
 */
public class FindCommand extends Command {
    private String filterParameter;

    /**
     * Constructs a find object for filtering of task based on its description.
     */
    public FindCommand(String filterParameter) {
        this.filterParameter = filterParameter;
    }

    /**
     * Executes the command to save find a task with description containing the specified keyword.
     *
     * @param tasks   List of tasks.
     * @param ui      User interface controller.
     * @param storage Storage controller for file manipulation.
     */
    @Override
    public Response execute(TaskList tasks, Ui ui, Storage storage, History history) throws OllieException {
        assert (filterParameter != null);
        history.add(new EmptyCommand());
        return new Response(ui.getTaskListMessage(tasks.filterByString(this.filterParameter)), false);
    }
}

