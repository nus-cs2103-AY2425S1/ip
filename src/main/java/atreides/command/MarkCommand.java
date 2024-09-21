package atreides.command;

import atreides.task.Task;
import atreides.task.TaskList;
import atreides.ui.AtreidesException;
import atreides.ui.Storage;
import atreides.ui.Ui;


/**
 * This class represents a command that marks a task as completed in a task list.
 */
public class MarkCommand implements Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String executeString(TaskList tasks, Ui ui, Storage storage) throws AtreidesException {
        tasks.mark(index);
        Task task = tasks.getTaskAtIndex(index);
        return "Thank you, one task completed: \n"
                + task;
    }

    /**
     * The task indicated by the index will be marked as completed
     * Ui will acknowledge that the task has been completed
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
