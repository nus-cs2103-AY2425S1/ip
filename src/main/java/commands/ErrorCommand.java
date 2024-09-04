package commands;

import windebot.History;
import windebot.Reminder;
import windebot.Ui;

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
     * @param history The History object used to save the data
     * @return false to indicate that the command was not successfully executed.
     */
    public boolean execute(String input, Reminder reminder, Ui ui, History history) {
        ui.print("You inputted a wrong input stoopid");
        return false;
    }

    /**
     * Gets the type of command: Add, ChangeMark or Delete
     */

    public String whatCommand() {
        return "";
    }
}
