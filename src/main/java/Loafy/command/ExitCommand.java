package loafy.command;

import loafy.tasklist.TaskList;
import loafy.ui.Ui;

/**
 * Represents a command to terminate the Loafy program.
 */
public class ExitCommand extends Command {

    /**
     * Prints the exit message.
     *
     * @param tasks Task list used in the program.
     * @param ui User interface which will print the message.
     */
    public String execute(TaskList tasks, Ui ui) {
        return ui.showExit();
    }

    /**
     * Returns {@code true}.
     * @return {@code true}.
     */
    public boolean isExit() {
        return true;
    }
}
