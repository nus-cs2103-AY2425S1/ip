package monobot.command;

import monobot.util.Storage;
import monobot.task.Task;
import monobot.util.TaskList;
import monobot.util.Ui;
import monobot.exception.MonoBotException;

/**
 * Represents delete command to delete a task from list.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs an DeleteCommand to delete a task.
     *
     * @param index index of Task to be deleted from list.
     */
    public DeleteCommand(int index) {
        super(CommandType.DELETE);
        this.index = index;
    }

    /**
     * Executes delete command.
     *
     * @param tasks list from which task will be deleted.
     * @param ui ui object to handle output to user.
     * @param storage storage object to read/write file.
     * @throws MonoBotException IF unable to delete task
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MonoBotException {
        Task deletedTask = tasks.getTask(index);
        tasks.deleteTask(index);
        ui.printDeletedTask(deletedTask, tasks.size());
        storage.save(tasks.getTasks());
    }
}
