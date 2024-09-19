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
     * @return The message to be displayed after executing the command.
     */
    @Override
    public String executeCmd(TaskList list, Ui ui) {
        String message = "";

        ui.showHorizontalLine();
        message = ui.showMessage(list.printList());
        ui.showHorizontalLine();

        return message;
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

