package monobot.command;

import monobot.util.Storage;
import monobot.util.TaskList;
import monobot.util.Ui;
import monobot.exception.MonoBotException;

/**
 * Represents unmark command to mark a task as incomplete.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Constructs a UnmarkCommand with the specified task index.
     *
     * @param index index of the task to be unmarked.
     */
    public UnmarkCommand(int index) {
        super(CommandType.UNMARK);
        this.index = index;
    }

    /**
     * Executes unmark command.
     *
     * @param tasks list of tasks containing task to unmark.
     * @param ui ui object to handle output to user.
     * @param storage storage object to read/write file.
     * @throws MonoBotException IF unable to unmark task
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MonoBotException {
        tasks.unmarkTask(index);
        ui.printUnmarkedTask(tasks.getTask(index));
        storage.save(tasks.getTasks());
    }
}
