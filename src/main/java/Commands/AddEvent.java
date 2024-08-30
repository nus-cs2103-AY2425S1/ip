package Commands;

import Tasks.Event;
import WindeBot.Reminder;
import WindeBot.Ui;

import Exceptions.EmptyDescriptionException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddEvent extends Command {

    public boolean execute(String input, Reminder reminder, Ui ui) throws EmptyDescriptionException {
        String[] command = input.split(" ", 2);
        if (command.length == 2) {
            String[] order = command[1].split(" /from ");
            if (order.length == 2) {
                String[] fillerName = order[1].split(" /to ");
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                LocalDateTime start = LocalDateTime.parse(fillerName[0], dateFormatter);
                LocalDateTime end = LocalDateTime.parse(fillerName[1], dateFormatter);
                Event e = new Event(order[0], start, end);
                reminder.addEvent(e);
                ui.print("Got it. I've added this task:");
                ui.print("    " + e.toString());
                ui.print("Now you have " + reminder.size() + " tasks in the list.");
            } else {
                throw new EmptyDescriptionException("WHEN EVENT DATE!");
            }
        } else {
            throw new EmptyDescriptionException("I NEED TO KNOW WHAT I'M EVENT-ING!");
        }
        return true;
    }
}
