package atreides.command;

import atreides.task.Task;
import atreides.task.TaskList;
import atreides.ui.AtreidesException;
import atreides.ui.Storage;
import atreides.ui.Ui;


/**
 * Represents a command to unmark a task as not done in the task list.
 */
public class UnMarkCommand implements Command {
    private final int index;

    public UnMarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String executeString(TaskList tasks, Ui ui, Storage storage) throws AtreidesException {
        tasks.unmark(index);
        Task task = tasks.getTaskAtIndex(index);
        return "Noted, this task has been unmarked\n"
                + task;
    }


    /**
     * The task indicated by the index will be marked as undone
     * Ui will acknowledge that the task has been marked as undone
     * @param tasks
     * @param ui
     * @param storage
     * @throws AtreidesException if index not in list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AtreidesException {
        String response = executeString(tasks, ui, storage);
        ui.showMessage(response);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
