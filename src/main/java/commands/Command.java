package commands;

import exceptions.EmptyDescriptionException;
import exceptions.TooManyParametersException;
import windebot.History;
import windebot.Reminder;
import windebot.Ui;

/**
 * The Command class is an abstract base class for all command types.
 * Subclasses of Command must implement the execute method to perform the command's action.
 */

public abstract class Command {

    /**
     * Executes the command with the provided input, reminder list, and UI.
     * Subclasses must provide an implementation for this method.
     *
     * @param input The user input string containing the command details.
     * @param reminder The Reminder object that manages the task list.
     * @param ui The Ui object used to interact with the user.
     * @return true if the command was executed successfully, false otherwise.
     * @throws EmptyDescriptionException If the input is incomplete or incorrectly formatted.
     * @throws TooManyParametersException If too many parameters are provided in the input.
     */

    public abstract boolean execute(String input, Reminder reminder, Ui ui)
            throws EmptyDescriptionException, TooManyParametersException;

    /**
     * Exits the Winde Chatbot
     * Saves the data stored in the Reminder to the
     *
     * @param history The History object that manages the txt file.
     * @param reminder The Reminder object that manages the task list.
     * @param ui The Ui object used to interact with the user.
     */

    public void exit(History history, Reminder reminder, Ui ui) {
        ui.print("Bye. Hope to see you again soon!");
        history.save(reminder.getSchedule());
    }
}
