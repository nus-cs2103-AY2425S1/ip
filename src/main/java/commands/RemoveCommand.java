package commands;

import exceptions.EmptyDescriptionException;
import exceptions.TooManyParametersException;
import tasks.Task;
import windebot.History;
import windebot.Reminder;
import windebot.Ui;

/**
 * The RemoveTask class represents a command to remove a task from the reminder list.
 * This command removes a specified task by its index in the list.
 * If the input is invalid or the task cannot be found, an exception is thrown.
 */

public class RemoveCommand extends Command {

    /**
     * Executes the RemoveTask command, removing a task from the reminder list.
     *
     * @param input The user input string containing the index of the task to be removed.
     * @param reminder The Reminder object that manages the task list.
     * @param ui The Ui object used to interact with the user.
     * @param history The History object used to save the data
     * @return true if the command was executed successfully.
     * @throws EmptyDescriptionException If no index is provided in the input.
     * @throws TooManyParametersException If too many parameters are provided in the input.
     */
    public boolean execute(String input, Reminder reminder, Ui ui, History history)
            throws EmptyDescriptionException, TooManyParametersException {
        String[] command = input.split(" ");
        assert(command.length == 2);
        if (command.length == 2) {
            Task deleted = reminder.remove(Integer.parseInt(command[1]) - 1);
            ui.print("Noted. I've removed this task:");
            ui.print("    " + deleted.toString());
            ui.print("Now you have " + reminder.size() + " tasks in the list.");
            history.save(reminder.getSchedule());
        } else if (command.length < 2) {
            throw new EmptyDescriptionException("I NEED TO KNOW WHAT I'M DELETING!");
        } else {
            throw new TooManyParametersException("ONE AT A TIME!");
        }
        return true;
    }

    /**
     * Gets the type of command: Add, ChangeMark or Delete
     */

    public String whatCommand() {
        return "DeleteCommand";
    }
}
