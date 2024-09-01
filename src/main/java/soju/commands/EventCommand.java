package soju.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import soju.SojuException;
import soju.Storage;
import soju.TaskList;
import soju.Ui;
import soju.tasks.Event;



/**
 * EventCommand handles commands starting with event
 */
public class EventCommand extends Command {
    private Event eventTask;

    /**
     * Returns an EventCommand
     * @param input command input as a String
     * @throws SojuException if invalid input
     */
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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String response = "";
        response += "Got it. I've added this task:\n";
        response += "  " + tasks.addTask(eventTask) + "\n";
        response += "Now you have " + tasks.size() + " tasks in the list.";
        ui.printString(response);
        return response;
    }
}
