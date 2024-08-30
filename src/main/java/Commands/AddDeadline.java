package Commands;

import Tasks.Deadline;
import WindeBot.Reminder;
import WindeBot.Ui;

import Exceptions.EmptyDescriptionException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
     * @return true if the command was executed successfully.
     * @throws EmptyDescriptionException If the input is incomplete or incorrectly formatted.
     */

    public boolean execute(String input, Reminder reminder, Ui ui) throws EmptyDescriptionException {
        String[] command = input.split(" ", 2);
        if (command.length == 2) {
            String[] order = command[1].split(" /by ");
            if (order.length == 2) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                LocalDateTime deadline = LocalDateTime.parse(order[1], formatter);
                Deadline d = new Deadline(order[0], deadline);
                reminder.addDeadline(d);
                ui.print("Got it. I've added this task:");
                ui.print("    " + d.toString());
                ui.print("Now you have " + reminder.size() + " tasks in the list.");
            } else {
                throw new EmptyDescriptionException("WHEN DEADLINE END!");
            }
        } else {
            throw new EmptyDescriptionException("I NEED TO KNOW WHAT I'M DEADLINING!");
        }
        return true;
    }
}
