package Commands;

import WindeBot.Reminder;
import WindeBot.Ui;

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
     * @return false to indicate that the program should exit.
     */

    public boolean execute(String input, Reminder reminder, Ui ui) {
        return false;
    }
}
