package monobot.command;

import java.util.List;

import monobot.exception.MonoBotException;
import monobot.task.Task;
import monobot.util.Storage;
import monobot.util.TaskList;
import monobot.util.Ui;

/**
 * Represents delete command to delete a task from list.
 */
public class DeleteCommand extends Command {
    private final List<Integer> indices;

    /**
     * Constructs an DeleteCommand to delete a task.
     *
     * @param indices indices of Tasks to be deleted from list.
     */
    public DeleteCommand(List<Integer> indices) {
        super(CommandType.DELETE);
        this.indices = indices;
    }

    /**
     * Executes delete command.
     *
     * @param tasks list from which task will be deleted.
     * @param ui ui object to handle output to user.
     * @param storage storage object to read/write file.
     * @throws MonoBotException IF unable to delete task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MonoBotException {
        List<Task> deletedTasks = tasks.deleteTasks(indices);
        ui.printDeletedTasks(deletedTasks, tasks.size());
        storage.save(tasks.getTasks());
    }
}
