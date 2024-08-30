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
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AtreidesException {
        tasks.mark(index);
        Task task = tasks.getTask(index);
        ui.showMessage("Thank you, one task completed: \n"
                + task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
