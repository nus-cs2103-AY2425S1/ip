package atreides.command;

import atreides.task.TaskList;
import atreides.ui.Storage;
import atreides.ui.Ui;

public class ErrorCommand implements Command {
    private final String error;

    public ErrorCommand(String error) {
        this.error = error;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError(error);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
