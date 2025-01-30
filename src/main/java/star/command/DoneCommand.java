package star.command;

import star.exception.StarException;
import star.main.Storage;
import star.main.TaskList;
import star.main.Ui;

/**
 * Represents the Command to mark a task in the task list as done.
 * Inherits from the Command class.
 */
public class DoneCommand extends Command {
    private int zeroIndex;

    /**
     * Constructs a doneCommand using the given index of the task from the printed task list.
     * @param oneIndex which is the printed task list's task index, which is also array index + 1.
     */
    public DoneCommand(int oneIndex) {
        this.zeroIndex = oneIndex - 1;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws StarException {
        tasks.markDone(zeroIndex);
        storage.save(tasks.getTasks());
        return ui.doneMsg(tasks.getTask(zeroIndex));
    }
}
