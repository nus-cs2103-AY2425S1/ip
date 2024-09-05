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
     * @param taskList Task list used in the program.
     * @param ui User interface which will print the message.
     */
    public void execute(TaskList taskList, Ui ui) {
        ui.showExit();
    }

    /**
     * Returns {@code true}.
     * @return {@code true}.
     */
    public boolean isExit() {
        return true;
    }
}
