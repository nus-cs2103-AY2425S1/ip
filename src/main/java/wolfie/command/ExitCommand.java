package wolfie.command;

import wolfie.task.TaskList;
import wolfie.util.Storage;
import wolfie.util.Ui;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {
    /**
     * Executes the command to exit the program.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface of the program.
     * @param storage The storage of the program.
     * @return The goodbye message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showGoodbye();
    }

    /**
     * Indicates that this command will exit the program.
     *
     * @return true, as this command will exit the program.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
