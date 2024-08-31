package colress.command;

import colress.TaskList;
import colress.Ui;

/**
 * Represents the exit command that exits the program.
 */
public final class ExitCommand extends Command {
    public ExitCommand() {
        super("");
    }

    /**
     * Facilitates exiting the program by calling the exit method of the provided Ui object. The provided TaskList
     * object is irrelevant in this method.
     */
    @Override
    public void execute(Ui ui, TaskList taskList) {
        ui.exit();
    }
}
