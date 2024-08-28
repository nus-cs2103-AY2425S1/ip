package pikappi.command;

import pikappi.Storage;
import pikappi.TaskList;
import pikappi.Ui;

/**
 * Represents a command by user to exit the program.
 */
public class ExitCommand extends Command {

    /**
     * Creates a new ExitCommand object.
     */
    public ExitCommand() {
    }

    /**
     * Exits the program and saves the current list of tasks to <code>storage</code>.
     *
     * @param tasks List of tasks.
     * @param ui Ui object that interacts with the user.
     * @param storage The storage object.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.goodbye();
        storage.save(tasks);
    }
}
