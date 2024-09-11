package monobot.command;

import java.util.List;

import monobot.exception.MonoBotException;
import monobot.task.Task;
import monobot.util.Storage;
import monobot.util.TaskList;
import monobot.util.Ui;

/**
 * Represents mark command to mark a task as complete.
 */
public class MarkCommand extends Command {
    private final List<Integer> indices;

    /**
     * Constructs a MarkCommand with the specified task indices.
     *
     * @param indices indices of the tasks to be marked as complete.
     */
    public MarkCommand(List<Integer> indices) {
        super(CommandType.MARK);
        this.indices = indices;
    }

    /**
     * Executes mark command.
     *
     * @param tasks list of tasks containing task to mark.
     * @param ui ui object to handle output to user.
     * @param storage storage object to read/write file.
     * @throws MonoBotException IF unable to mark task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MonoBotException {
        List<Task> markedTasks = tasks.markTasks(indices);
        ui.printMarkedTasks(markedTasks);
        storage.save(tasks.getTasks());
    }
}
