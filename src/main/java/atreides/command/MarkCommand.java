package atreides.command;

import atreides.task.Task;
import atreides.task.TaskList;
import atreides.ui.AtreidesException;
import atreides.ui.Storage;
import atreides.ui.Ui;

public class MarkCommand implements Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
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
        tasks.mark(index);
        Task task = tasks.getTask(index);
        ui.showMessage("Thank you, one task completed: \n"
                + task);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
