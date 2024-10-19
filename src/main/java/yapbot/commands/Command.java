package yapbot.commands;

import yapbot.exceptions.YapBotException;
import yapbot.util.Storage;
import yapbot.util.TaskList;

/**
 * Abstract parent class that all commands must inherit from.
 */
public abstract class Command {

    /**
     * @param tasks TaskList to be called for any task-related functions.
     * @param storage storage to be called for any file interactions.
     * @return Response String for YapBot.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws YapBotException;

    /**
     * Returns whether YapBot should terminate for this Command.
     *
     * @return true if the command is a terminating command and false otherwise.
     */
    public boolean isExit() {
        return false;
    }

}
