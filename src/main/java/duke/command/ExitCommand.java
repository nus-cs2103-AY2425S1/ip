package duke.command;

import duke.Archive;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents an exit command.
 */
public class ExitCommand extends Command {
    /**
     * Constructor for an exit command.
     */
    public ExitCommand() {}

    /**
     * Writes the exit message.
     *
     * @param tasks The list of tasks for the command to interact with.
     * @param ui The ui for the command to interact with.
     * @param storage The storage for the command to interact with.
     * @param archive The archive for the command to interact with.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, Archive archive) {
        ui.exit();
    }

    /**
     * Returns true for whether the program should exit after execution.
     *
     * @return True for whether the program should exit after execution.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
