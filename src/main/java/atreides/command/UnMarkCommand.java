package atreides.command;

import atreides.task.Task;
import atreides.task.TaskList;
import atreides.ui.AtreidesException;
import atreides.ui.Storage;
import atreides.ui.Ui;

public class UnMarkCommand implements Command {
    private final int index;

    public UnMarkCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AtreidesException {
        tasks.unmark(index);
        Task task = tasks.getTask(index);
        ui.showMessage("Noted, this task has been unmarked\n"
                + task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
