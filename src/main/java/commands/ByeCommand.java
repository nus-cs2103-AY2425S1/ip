package commands;

import windebot.History;
import windebot.Reminder;
import windebot.Ui;

/**
 * The ByeCommand class represents a command to exit the application.
 * This command is typically used to end the program gracefully.
 */

public class ByeCommand extends Command {

    /**
     * Executes the ByeCommand, signaling the end of the application.
     *
     * @param input The user input string (not used in this command).
     * @param reminder The Reminder object that manages the task list (not used in this command).
     * @param ui The Ui object used to interact with the user.
     * @param history The History object used to save the data
     * @return false to indicate that the program should exit.
     */

    public boolean execute(String input, Reminder reminder, Ui ui, History history) {
        return false;
    }

    /**
     * Gets the type of command: Add, ChangeMark or Delete
     */

    public String whatCommand() {
        return "";
    }
}
