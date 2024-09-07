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
    public String executeString(TaskList tasks, Ui ui, Storage storage) {
        return error;
    }

    /**
     * Ui will show the error message
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String error = executeString(tasks, ui, storage);
        ui.showError(error);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
