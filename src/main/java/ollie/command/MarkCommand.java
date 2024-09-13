package ollie.command;

import ollie.*;
import ollie.exception.OllieException;
import ollie.task.Task;

/**
 * Represents a command a particular task as done.
 */
public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the marking of a task at a particular index as done.
     *
     * @param tasks   List of tasks.
     * @param ui      User interface controller.
     * @param storage Storage controller for file manipulation.
     */
    @Override

    public Response execute(TaskList tasks, Ui ui, Storage storage, History history) throws OllieException {
        Task task = tasks.markAsDone(index);
        history.add(new UnmarkCommand(index));
        return new Response(ui.getMarkAsDoneMessage(task), false);
    }
}
