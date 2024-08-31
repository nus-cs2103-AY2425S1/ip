package atreides.command;

import atreides.task.Task;
import atreides.task.TaskList;
import atreides.ui.AtreidesException;
import atreides.ui.Storage;
import atreides.ui.Ui;

public class DeleteCommand implements Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the specific task based on its index
     * @param tasks
     * @param ui
     * @param storage
     * @throws AtreidesException if index not found
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AtreidesException {
        Task task = tasks.delete(index);
        String plural = tasks.size() == 1 ? " task" : " tasks";
        String response = "atreides.task.Task removed: \n" +
                task.toString().indent(2) +
                + tasks.size() + plural + " in list\n";
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
