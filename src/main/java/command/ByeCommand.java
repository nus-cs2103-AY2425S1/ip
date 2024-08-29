package command;

import tasklist.TaskList;
import ui.CommandLineUi;

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
     * @param ui       The CommandLineUI, which is not used in this command.
     */
    public void execute(TaskList tasklist, CommandLineUi ui) {
        // Do nothing
    }

    /**
     * Indicates whether this command causes the application to exit.
     *
     * @return true, as this command signals the application to exit.
     */
    public boolean isExit() {
        return true;
    }
}
