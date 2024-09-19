package fanny.command;

import fanny.task.TaskList;
import fanny.ui.Ui;

/**
 * Represents the command that handles the "remind" prompt.
 */
public class RemindCommand extends Command {

    /**
     * Executes the "remind" command by displaying a list of tasks due
     * in the next 24 hours.
     *
     * @param list The current list of tasks.
     * @param ui The UI object to interact with the user.
     * @return The reminder message to be displayed.
     */
    @Override
    public String executeCmd(TaskList list, Ui ui) {
        return ui.showReminders(list);
    }

    /**
     * Indicates that executing this command should not exit the application.
     *
     * @return {@code false}, indicating that the application should not exit.
     */
    @Override
    public boolean shouldExit() {
        return false;
    }
}
