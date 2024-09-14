package monobot.command;

import java.util.List;

import monobot.exception.MonoBotException;
import monobot.task.Task;
import monobot.util.Storage;
import monobot.util.TaskList;
import monobot.util.Ui;

/**
 * Represents unmark command to mark a task as incomplete.
 */
public class UnmarkCommand extends Command {
    private final List<Integer> indices;

    /**
     * Constructs a UnmarkCommand with the specified task indices.
     *
     * @param indices indices of the task to be unmarked.
     */
    public UnmarkCommand(List<Integer> indices) {
        super(CommandType.UNMARK);
        this.indices = indices;
    }

    /**
     * Executes unmark command.
     *
     * @param tasks list of tasks containing task to unmark.
     * @param ui ui object to handle output to user.
     * @param storage storage object to read/write file.
     * @throws MonoBotException IF unable to unmark task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MonoBotException {
        List<Task> unmarkedTasks = tasks.unmarkTasks(indices);
        ui.printUnmarkedTasks(unmarkedTasks);
        storage.save(tasks.getTasks());
    }
}
