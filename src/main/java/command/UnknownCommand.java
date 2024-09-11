package command;

import main.Storage;
import main.TaskList;

/**
 * Represents a command that handles unknown or unrecognized commands.
 */
public class UnknownCommand extends Command {

    /**
     * Constructs an UnknownCommand instance.
     */
    public UnknownCommand() {
        // No specific initialization required
    }

    /**
     * Executes the UnknownCommand. This method currently does nothing but can be
     * extended to handle unknown commands appropriately.
     *
     * @param tasks The TaskList to operate on.
     * @param storage The Storage to operate on.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        // Implementation for handling unknown commands can be added here
        return "Unknown command.";
    }
}
