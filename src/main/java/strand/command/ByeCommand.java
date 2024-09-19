package strand.command;

import strand.Storage;
import strand.TaskList;
import strand.Ui;

/**
 * The {@code ByeCommand} class represents a command to exit the program.
 */
public class ByeCommand extends Command {

    /**
     * Executes the bye command.
     *
     * @param tasks   The current list of tasks.
     * @param ui      The {@code Ui} object used to display messages to the user.
     * @param storage The {@code Storage} object responsible for saving/loading tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.goodbye();
    }

    /**
     * Indicates that the program should terminate after executing this command.
     *
     * @return {@code false}, indicating that the program is no longer running.
     */
    @Override
    public Boolean isRunning() {
        return false;
    }
}
