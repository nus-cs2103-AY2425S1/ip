package commands;

import exceptions.EmptyDescriptionException;
import exceptions.IndexOutBoundsException;
import exceptions.NotIntegerException;
import windebot.History;
import windebot.Reminder;
import windebot.Ui;

/**
 * The AddUnmark class represents a command to unmark a task as done in the reminder list.
 * This command unmarks a specified task by its index in the list.
 * If the input is invalid or the task cannot be found, an exception is thrown.
 */

public class AddUnmark extends Command {

    /**
     * Executes the AddUnmark command, marking a task as not completed.
     *
     * @param input The user input string containing the index of the task to be marked.
     * @param reminder The Reminder object that manages the task list.
     * @param ui The Ui object used to interact with the user.
     * @param history The History object used to save the data
     * @return true if the command was executed successfully.
     * @throws EmptyDescriptionException If no index is provided in the input.
     * @throws NotIntegerException If no integer is provided in the input.
     * @throws IndexOutBoundsException If integer provided is out of bounds.
     */

    public boolean execute(String input, Reminder reminder, Ui ui, History history)
            throws NotIntegerException, IndexOutBoundsException, EmptyDescriptionException {
        try {
            String[] command = input.split(" ", 2);
            if (command.length < 2) {
                throw new EmptyDescriptionException();
            }
            String search = command[1].trim();
            if (!(search.equals(""))) {
                int index = Integer.parseInt(command[1].trim());
                if (index > reminder.size() + 1) {
                    throw new IndexOutOfBoundsException();
                }
                reminder.unmark(index - 1);
                ui.print("Ok! I've marked this task as not done:");
                ui.print("    " + reminder.getTask(Integer.parseInt(command[1]) - 1).toString());
                history.save(reminder.getSchedule());
            } else {
                throw new EmptyDescriptionException();
            }
        } catch (NumberFormatException e) {
            ui.notIntegerMessage();
        } catch (IndexOutOfBoundsException e) {
            ui.indexOutBoundsMessage();
        } catch (EmptyDescriptionException e) {
            ui.emptyDescriptionMessage();
        }
        return true;
    }

    /**
     * Gets the type of command: Add, ChangeMark or Delete
     */

    public String whatCommand() {
        return "ChangeMarkCommand";
    }
}
