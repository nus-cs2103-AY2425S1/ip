package Commands;

import Tasks.Deadline;
import WindeBot.Reminder;
import WindeBot.Ui;

import Exceptions.EmptyDescriptionException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddDeadline extends Command {
    public boolean execute(String input, Reminder reminder, Ui ui) throws EmptyDescriptionException {
        String[] command = input.split(" ", 2);
        if (command.length == 2) {
            String[] order = command[1].split(" /by ");
            if (order.length == 2) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                LocalDateTime deadline = LocalDateTime.parse(order[1], formatter);
                Deadline deadlineTask = new Deadline(order[0], deadline);
                reminder.addDeadline(deadlineTask);
                ui.print("Got it. I've added this task:");
                ui.print("    " + deadlineTask.toString());
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
