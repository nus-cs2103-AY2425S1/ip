package karen.commands;

import karen.tasks.TaskList;
import karen.util.Ui;

/**
 * Handles unknown inputs from the user and prints the appropriate message
 */
public class UnknownCommand extends Command {

    @Override
    public String execute(TaskList taskList, Ui ui) {
        return ui.showUnknownInputError();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
