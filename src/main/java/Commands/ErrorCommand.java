package Commands;

import WindeBot.Reminder;
import WindeBot.Ui;

/**
 * The ErrorCommand class represents a command that handles invalid user input.
 * This command simply displays an error message when executed.
 */

public class ErrorCommand extends Command {

    /**
     * Executes the ErrorCommand, displaying an error message to the user.
     *
     * @param input The user input string (not used in this command).
     * @param reminder The Reminder object that manages the task list (not used in this command).
     * @param ui The Ui object used to interact with the user.
     * @return false to indicate that the command was not successfully executed.
     */
    public boolean execute(String input, Reminder reminder, Ui ui) {
        ui.print("You inputted a wrong input stoopid");
        return false;
    }
}
