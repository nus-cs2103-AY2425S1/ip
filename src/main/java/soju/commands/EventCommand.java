package soju.commands;

import soju.*;
import soju.tasks.Event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventCommand extends Command {
    Event eventTask;
    public EventCommand(String input) throws SojuException {
        try {
            String[] parts = input.substring(6).split(" /from ", 2);
            String description = parts[0].trim(); // This is the task description
            if (description.isEmpty()) {
                throw new SojuException("The description of a todo cannot be empty.");
            }
            String[] timeParts = parts[1].split(" /to ", 2);
            String from = timeParts[0].trim(); // Start time
            String to = timeParts[1].trim(); // End time
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime localFromDate = LocalDateTime.parse(from, formatter);
            LocalDateTime localToDate = LocalDateTime.parse(to, formatter);
            // Create a new Tasks.Event task
            eventTask = new Event(description, localFromDate, localToDate);
        } catch (Exception e) {
            throw new SojuException(e.getMessage());
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printString("Got it. I've added this task:");
        ui.printString("  " + tasks.addTask(eventTask));
        ui.printString("Now you have " + tasks.size() + " tasks in the list.");
    }
}
