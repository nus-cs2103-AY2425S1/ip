package michaelscott.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import michaelscott.task.Event;
import michaelscott.task.TaskList;
import michaelscott.utils.MichaelScottException;

/**
 * Represents a command to create a new event task.
 * This class parses the input string to extract the event description, start time, and end time,
 * and creates a new Event task when executed.
 */
public class EventCommand implements Command {
    private final String description;
    private final LocalDateTime toDate;
    private final LocalDateTime fromDate;

    /**
     * Constructs a new EventCommand by parsing the given arguments.
     *
     * @param args The string containing the event description, start time, and end time.
     * @throws MichaelScottException If the input format is invalid or the dates cannot be parsed.
     */
    public EventCommand(String args) throws MichaelScottException {
        String[] eventParts = args.split(" /from | /to ");
        if (eventParts.length != 3) {
            throw new MichaelScottException(
                    "Please provide the event description, start time (/from), and end time(/to). \n"
                            + "Here is an example: Event Career fair /from 2024-02-02 12:00 /to 2024-02-02 17:00."
            );
        }
        description = eventParts[0];
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            toDate = LocalDateTime.parse(eventParts[1], formatter);
            fromDate = LocalDateTime.parse(eventParts[2], formatter);
        } catch (java.time.format.DateTimeParseException e) {
            throw new MichaelScottException(
                    "Invalid date format: Please use the format YYYY-MM-DD HH:MM."
            );
        }
    }

    @Override
    public String execute(TaskList tasks) {
        Event eventTask = new Event(this.description, this.toDate, this.fromDate);
        tasks.addTask(eventTask);
        return "Got it. I've added this task: \n"
                + "   " + eventTask.toString() + "\n"
                + "Now you have " + tasks.size()
                + (tasks.size() > 1 ? " tasks " : " task ") + "in the list.";
    }
}
