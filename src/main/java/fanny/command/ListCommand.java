package fanny.command;

import fanny.task.TaskList;
import fanny.ui.Ui;

/**
 * Represents the command that handles the "list" prompt.
 */
public class ListCommand extends Command {

    /**
     * Executes the "list" command by displaying the list of tasks.
     *
     * @param list The current list of tasks.
     * @param ui The UI object to interact with the user.
     */
    @Override
    public void executeCmd(TaskList list, Ui ui) {
        ui.showHorizontalLine();
        ui.showMessage("Fanny:");
        list.printList();
        ui.showHorizontalLine();
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

