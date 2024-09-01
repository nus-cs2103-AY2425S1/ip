package commands;

import skibidi.Ui;
import skibidi.Command;
import storage.TaskStorage;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {
    /**
     * Executes the command to exit the program.
     *
     * @param ui The user interface to interact with the user.
     * @param storage The task storage to store the task.
     * @return False to indicate the program should exit.
     */
    @Override
    public boolean execute(Ui ui, TaskStorage storage) {
        ui.showGoodbye();
        return false;
    }
}
