package commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exceptions.EmptyDescriptionException;
import exceptions.InvalidDateFormatException;
import tasks.Deadline;
import windebot.History;
import windebot.Reminder;
import windebot.Ui;

/**
 * The AddDeadline class represents a command to add a deadline task to the reminder list.
 * This command parses the input to extract the task description and deadline date.
 * If the input is invalid or incomplete, an exception is thrown.
 */

public class AddDeadline extends Command {
    /**
     * Executes the AddDeadline command, adding a new deadline task to the reminder.
     *
     * @param input The user input string containing the task description and deadline.
     * @param reminder The Reminder object that manages the task list.
     * @param ui The Ui object used to interact with the user.
     * @param history The History object used to save the data
     * @return true if the command was executed successfully.
     * @throws EmptyDescriptionException If the input is incomplete or incorrectly formatted.
     */

    public boolean execute(String input, Reminder reminder, Ui ui, History history)
            throws EmptyDescriptionException, InvalidDateFormatException {
        String[] command = input.split(" ", 2);
        String[] order = command[1].split(" /by ");
        String taskDescription = order[0].trim();
        try {
            if (!(taskDescription.equals("")) && (order.length == 3)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                LocalDateTime deadline = LocalDateTime.parse(order[1], formatter);
                Deadline deadlineTask = new Deadline(taskDescription, deadline);
                reminder.addDeadline(deadlineTask);
                ui.print("Got it. I've added this task:");
                ui.print("    " + deadlineTask.toString());
                ui.print("Now you have " + reminder.size() + " tasks in the list.");
                history.save(reminder.getSchedule());
            } else {
                throw new EmptyDescriptionException("ADD THE CORRECT PARAMETERS!");
            }
            return true;
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException("FOLLOW THE CORRECT DATE FORMAT: DD/MM/YYYY HH:MM");
        }
    }

    /**
     * Gets the type of command: Add, ChangeMark or Delete
     */

    public String whatCommand() {
        return "AddCommand";
    }
}
