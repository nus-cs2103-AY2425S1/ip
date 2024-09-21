package atreides.command;

import atreides.task.TaskList;
import atreides.ui.Storage;
import atreides.ui.Ui;

/**
 * Represents an error command that can be used to encapsulate and display an error message.
 */
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
     * Executes the error command by displaying the error message.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface for displaying messages.
     * @param storage The storage handler for saving and loading tasks.
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
