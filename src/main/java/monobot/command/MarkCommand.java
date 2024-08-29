package monobot.command;

import monobot.util.Storage;
import monobot.util.TaskList;
import monobot.util.Ui;
import monobot.exception.MonoBotException;

/**
 * Represents mark command to mark a task as complete.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructs a MarkCommand with the specified task index.
     *
     * @param index index of the task to be marked as complete.
     */
    public MarkCommand(int index) {
        super(CommandType.MARK);
        this.index = index;
    }

    /**
     * Executes mark command.
     *
     * @param tasks list of tasks containing task to mark.
     * @param ui ui object to handle output to user.
     * @param storage storage object to read/write file.
     * @throws MonoBotException IF unable to mark task
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MonoBotException {
        tasks.markTask(index);
        ui.printMarkedTask(tasks.getTask(index));
        storage.save(tasks.getTasks());
    }
}
