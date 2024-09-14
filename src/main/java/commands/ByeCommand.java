package commands;

import cook.Cook;
import cook.Storage;
import cook.TaskList;

/**
 * ByeCommand class to process bye commands.
 */
public class ByeCommand extends Command {

    /**
     * Constructs ByeCommand object.
     */
    public ByeCommand() {
        super("bye");
    }

    /**
     * Tells Cook to exit, and returns bye.
     *
     * @param tasks List of Task objects.
     * @param storage Class to save and load tasks on the local drive.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        Cook.exit();
        return "Bye.";
    }
}
