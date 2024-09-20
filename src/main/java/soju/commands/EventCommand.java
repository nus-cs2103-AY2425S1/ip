package soju.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Collectors;

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
            String[] taskDetails = input.substring(6).split(" /from ", 2);
            String description = taskDetails[0].trim(); // This is the task description
            if (description.isEmpty()) {
                throw new SojuException("The description of a todo cannot be empty.");
            }
            String[] timeParts = taskDetails[1].split(" /to ", 2);
            String from = timeParts[0].trim(); // Start time
            String to = timeParts[1].trim(); // End time
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime localFromDate = LocalDateTime.parse(from, formatter);
            LocalDateTime localToDate = LocalDateTime.parse(to, formatter);
            if (localToDate.isBefore(localFromDate)) {
                throw new SojuException("The end time cannot be before the start time.");
            }
            // Create a new Tasks.Event task
            eventTask = new Event(description, localFromDate, localToDate);
        } catch (Exception e) {
            throw new SojuException(e.getMessage());
        }
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Event[] clashingEvents =
                tasks.getTasks().stream()
                        .filter(task -> task instanceof Event)
                        .filter(event -> ((Event) event).isClashing(eventTask))
                        .toArray(Event[]::new);
        String response = "";
        if (clashingEvents.length != 0) {
            response += "Error adding " + eventTask + " due to these tasks:\n";
            response += Arrays.stream(clashingEvents)
                    .map(task -> (tasks.getTasks().indexOf(task) + 1) + "." + task)
                    .collect(Collectors.joining("\n"));
            response += "Aborting...";
        } else {
            response += "Got it. I've added this task:\n";
            response += "  " + tasks.addTask(eventTask) + "\n";
            response += "Now you have " + tasks.size() + " tasks in the list.";
        }
        ui.printString(response);
        return response;
    }
}
