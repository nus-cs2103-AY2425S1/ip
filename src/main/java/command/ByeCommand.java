package command;

import tasklist.TaskList;

/**
 * Represents a command to exit the application.
 */
public class ByeCommand extends Command {
    /** Index or additional information, which is currently not used in this command. */
    protected int index;

    /**
     * Executes the command, which in this case, does nothing as the command is meant to signal the application to exit.
     *
     * @param tasklist The TaskList, which is not used in this command.
     * @return null.
     */
    @Override
    public String execute(TaskList tasklist) {
        // Do nothing
        return null;
    }

    /**
     * Indicates whether this command causes the application to exit.
     *
     * @return true, as this command signals the application to exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
