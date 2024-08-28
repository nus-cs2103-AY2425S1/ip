package MichaelScott.Command;

import MichaelScott.Task.Event;
import MichaelScott.Exception.MichaelScottException;
import MichaelScott.Task.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a command to add a new event task to the task list.
 * This command parses the user input to create a new Event task with a description, start time, and end time.
 */
public class EventCommand implements Command {
    private final String description;
    private final LocalDateTime toDate;
    private final LocalDateTime fromDate;

    /**
     * Constructs a new EventCommand by parsing the given arguments.
     *
     * @param args The command arguments containing the event description, start time, and end time.
     *             Expected format: "description /from YYYY-MM-DD HH:MM /to YYYY-MM-DD HH:MM"
     * @throws MichaelScottException If the input format is invalid or the dates cannot be parsed.
     */
    public EventCommand(String args) throws MichaelScottException {
        String[] eventParts = args.split(" /from | /to ");
        if (eventParts.length != 3) {
            throw new MichaelScottException("Please provide the event description, start time (/from), and end time(/to). Example: MichaelScott.MichaelScott.task.Event Career fair /from 2024-02-02 12:00 /to 2024-02-02 17:00.");
        }
        description = eventParts[0];
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            toDate = LocalDateTime.parse(eventParts[1], formatter);
            fromDate = LocalDateTime.parse(eventParts[2], formatter);
        } catch (java.time.format.DateTimeParseException e) {
            throw new MichaelScottException("Invalid date format. Please use the format YYYY-MM-DD HH:MM.");
        }
    }

    @Override
    public String execute(TaskList tasks) {
        Event EventTask = new Event(this.description, this.toDate, this.fromDate);
        tasks.addTask(EventTask);
        return "Got it. I've added this task: \n" +
                "   " + EventTask.toString() + "\n" +
                "Now you have " + tasks.size() + (tasks.size() > 1 ? " tasks " : " task ") + "in the list.";
    }
}
