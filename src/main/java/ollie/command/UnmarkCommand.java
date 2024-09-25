package ollie.command;

import ollie.History;
import ollie.Response;
import ollie.Storage;
import ollie.TaskList;
import ollie.Ui;
import ollie.exception.OllieException;
import ollie.task.Task;

/**
 * Represents a command for marking a particular task as undone.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs a command object for marking a task as not complete.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the marking of task at a particular index in the list as undone.
     *
     * @param tasks   List of tasks.
     * @param ui      User interface controller.
     * @param storage Storage controller for file manipulation.
     */
    @Override

    public Response execute(TaskList tasks, Ui ui, Storage storage, History history) throws OllieException {
        Task task = tasks.markAsUndone(index);
        history.add(new MarkCommand(index));
        return new Response(ui.getMarkAsUndoneMessage(task), false);
    }
}
