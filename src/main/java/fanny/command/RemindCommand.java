package fanny.command;

import fanny.task.TaskList;
import fanny.ui.Ui;

public class RemindCommand extends Command {

    /**
     * Executes the "bye" command by displaying a goodbye message and closing the UI.
     *
     * @param list The current list of tasks.
     * @param ui The UI object to interact with the user.
     */
    @Override
    public String executeCmd(TaskList list, Ui ui) {
        return ui.showReminders(list);
    }

    /**
     * Indicates that executing this command should exit the application.
     *
     * @return {@code true}, indicating that the application should exit.
     */
    @Override
    public boolean shouldExit() {
        return false;
    }
}
