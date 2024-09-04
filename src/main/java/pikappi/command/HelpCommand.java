package pikappi.command;

import pikappi.Storage;
import pikappi.TaskList;
import pikappi.Ui;

/**
 * Represents a command by user to exit the program.
 */
public class HelpCommand extends Command {

    /**
     * Creates a new ExitCommand object.
     */
    public HelpCommand() {
    }

    /**
     * Exits the program and saves the current list of tasks to <code>storage</code>.
     *
     * @param tasks List of tasks.
     * @param ui Ui object that interacts with the user.
     * @param storage The storage object.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showHelp();
    }
}
