package duke.command;

import duke.Archive;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a no operation command.
 */
public class NoopCommand extends Command {
    /**
     * Constructor for a no operation command.
     */
    public NoopCommand() {}

    /**
     * Does nothing.
     *
     * @param tasks The list of tasks for the command to interact with.
     * @param ui The ui for the command to interact with.
     * @param storage The storage for the command to interact with.
     * @param archive The archive for the command to interact with.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, Archive archive) {

    }
}
