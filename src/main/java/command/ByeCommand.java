package command;

import main.Storage;
import main.TaskList;

/**
 * Represents a command to exit the application.
 */
public class ByeCommand extends Command {

    /**
     * Constructs a ByeCommand instance.
     */
    public ByeCommand() {
        // No specific initialization required for this command
    }

    /**
     * Returns true to indicate that this command is an exit command.
     *
     * @return true, as this command exits the application.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Executes the bye command. This implementation does not perform any actions.
     *
     * @param tasks   The task list (not used for this command).
     * @param storage The storage (not used for this command).
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        // No action needed for exit command
        return "";
    }
}
