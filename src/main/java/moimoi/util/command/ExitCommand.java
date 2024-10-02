package moimoi.util.command;

import moimoi.util.Storage;
import moimoi.util.TaskList;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {

    /**
     * Constructs a command to exit the program.
     */
    public ExitCommand() {
        super(true);
    }

    /**
     * Exits the program.
     *
     * @param storage MoiMoi's storage.
     * @param tasks List of existing tasks.
     * @return Exit response to be handled by the GUI.
     */
    @Override
    public String execute(Storage storage, TaskList tasks) {
        return "";
    }

}
