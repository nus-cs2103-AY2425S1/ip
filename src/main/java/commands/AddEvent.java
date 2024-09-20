package commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exceptions.EmptyDescriptionException;
import exceptions.InvalidDateFormatException;
import exceptions.TooManyParametersException;
import tasks.Event;
import windebot.History;
import windebot.Reminder;
import windebot.Ui;

/**
 * The AddEvent class represents a command to add an event task to the reminder list.
 * This command parses the input to extract the event description, start time, and end time.
 * If the input is invalid or incomplete, an exception is thrown.
 */

public class AddEvent extends Command {

    /**
     * Executes the AddEvent command, adding a new event task to the reminder.
     *
     * @param input The user input string containing the event description, start time, and end time.
     * @param reminder The Reminder object that manages the task list.
     * @param ui The Ui object used to interact with the user.
     * @param history The History object used to save the data
     * @return true if the command was executed successfully.
     * @throws InvalidDateFormatException If the input date is incorrectly formatted.
     */

    public boolean execute(String input, Reminder reminder, Ui ui, History history)
            throws EmptyDescriptionException, InvalidDateFormatException, TooManyParametersException {
        String[] command = input.split(" ", 2);
        String[] order = command[1].split(" /from | /to ");
        String taskDescription = order[0].trim();
        try {
            if (!(taskDescription.equals("")) && (order.length == 3)) {
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                LocalDateTime start = LocalDateTime.parse(order[1], dateFormatter);
                LocalDateTime end = LocalDateTime.parse(order[2], dateFormatter);
                Event eventTask = new Event(taskDescription, start, end);
                reminder.addEvent(eventTask);
                ui.print("Got it. I've added this task:");
                ui.print("    " + eventTask.toString());
                ui.print("Now you have " + reminder.size() + " tasks in the list.");
                history.save(reminder.getSchedule());
            } else if (order.length > 3) {
                throw new TooManyParametersException();
            } else {
                throw new EmptyDescriptionException();
            }
        } catch (DateTimeParseException e) {
            ui.invalidDateFormatMessage();
        } catch (EmptyDescriptionException e) {
            ui.emptyDescriptionMessage();
        } catch (TooManyParametersException e) {
            ui.tooManyParametersMessage();
        }
        return true;
    }

    /**
     * Gets the type of command: Add, ChangeMark or Delete
     */

    public String whatCommand() {
        return "AddCommand";
    }
}
