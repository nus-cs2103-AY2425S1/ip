package star.command;

import star.exception.StarException;
import star.main.Storage;
import star.main.TaskList;
import star.main.Ui;

/**
 * Represents the Command to mark a task in the task list as undone.
 * Inherits from the Command class.
 */
public class UndoneCommand extends Command {
    private int zeroIndex;

    /**
     * Constructs an undoneCommand using the given index of the task from the printed task list.
     * @param oneIndex which is the printed task list's task index, which is also array index + 1.
     */
    public UndoneCommand(int oneIndex) {
        this.zeroIndex = oneIndex - 1;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws StarException {
        tasks.markUndone(zeroIndex);
        storage.save(tasks.getTasks());
        return ui.undoneMsg(tasks.getTask(zeroIndex));
    }
}
