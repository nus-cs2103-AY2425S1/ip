package pandabot.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import pandabot.exceptions.InputException;

/**
 * Represents an Event task.
 * Event task is a task that has a specific start and end time, in addition to a description.
 */
public class Event extends Task {

    /** The start time of the event. */
    protected LocalDateTime from;

    /** The end time of the event. */
    protected LocalDateTime to;

    /**
     * Constructs a new Event task with the given description, start time, and end time.
     *
     * @param description the description of the Event task.
     * @param from the start time of the event.
     * @param to the end time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Creates an Event task from the given input string.
     * The input string must start with the command "event" followed by the task description,
     * the start time, and the end time.
     *
     * @param input the input string containing the task details.
     * @return the created Event task.
     * @throws InputException if the input format is incorrect or if the description is missing.
     */
    @Override
    public Task createTask(String input) throws InputException {
        if (input.equalsIgnoreCase("event")) {
            throw new InputException("To add an tasks.Event, use: event <description> /from <DD/MM/YYYY HHmm>"
                    + " /to <DD/MM/YYYY HHmm>");
        }
        String[] details = input.split(" /from | /to ");
        if (details.length == 3) {
            String description = details[0].trim();
            LocalDateTime from = parseDateTime(details[1].trim());
            LocalDateTime to = parseDateTime(details[2].trim());
            if (from.isEqual(to) || from.isAfter(to)) {
                throw new InputException("The start time for an event must be before the end time.");
            }
            if (description.isEmpty()) {
                throw new InputException("You need to describe your tasks.Event!");
            }
            return new Event(description, from, to);
        } else {
            throw new InputException("Invalid format. Use: event <description> /from <DD/MM/YYYY HHmm>"
                    + " /to <DD/MM/YYYY HHmm>");
        }
    }

    /**
     * Parses a datetime string into a LocalDateTime Object.
     *
     * @param dateTimeString the string containing the datetime
     * @return the LocalDateTime Object.
     * @throws InputException if the datetime format is invalid
     */
    private LocalDateTime parseDateTime(String dateTimeString) throws InputException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            return LocalDateTime.parse(dateTimeString, formatter);
        } catch (Exception e) {
            throw new InputException("Invalid date/time format. Use: DD/MM/YYYY HHmm (e.g. '12/11/2002 1800')");
        }
    }

    /**
     * Returns a string representation of the Event task.
     * The string includes a "[E]" prefix to indicate that it is an Event task, followed by the task status,
     * description, start time, and end time.
     *
     * @return a string representing the Event task.
     */

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
        return "[E]" + super.toString() + " (from: " + from.format(formatter) + ", to: " + to.format(formatter) + ")";
    }

    /**
     * Returns a string representation of an Event when saving to file.
     *
     * @return a string representation of the Event.
     */
    @Override
    public String encode() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from.format(formatter)
                + " | " + to.format(formatter);
    }
}
