package command;

import components.Storage;
import components.Ui;
import task.TaskList;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {

    /**
     * Creates an ExitCommand object.
     */
    public ExitCommand() {
        super.isExit = true;
    }

    /**
     * Exits the program.
     *
     * @param tasks The task list.
     * @param ui The user interface.
     * @param storage The storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage(" Bye. Hope to see you again soon!");
    }
}
