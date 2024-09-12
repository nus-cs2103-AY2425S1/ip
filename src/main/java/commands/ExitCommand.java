package commands;

import skibidi.Command;
import skibidi.Ui;
import storage.TaskStorage;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {
    /**
     * Executes the command to exit the program.
     *
     * @param ui      The user interface to interact with the user.
     * @param storage The task storage to store the task.
     * @return Output message to be displayed to the user.
     */
    @Override
    public String execute(Ui ui, TaskStorage storage) {
        assert ui != null : "Ui should not be null";
        assert storage != null : "TaskStorage should not be null";
        return ui.goodbyeText();
    }
}
