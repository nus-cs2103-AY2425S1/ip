package pandabot.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import pandabot.exceptions.InputException;

/**
 * Represents a Deadline task.
 * A Deadline task is a task with a description and a specific due date or time by which it should be completed.
 */
public class Deadline extends Task {

    /** The due date or time of the deadline. */
    protected LocalDateTime by;

    /**
     * Constructs a new Deadline task with the given description and due date.
     *
     * @param description the description of the Deadline task.
     * @param by the due date or time by which the task should be completed.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Creates a Deadline task from the given input string.
     * The input string must start with the command "deadline" followed by the task description and the due date.
     *
     * @param input the input string containing the task details.
     * @return the created Deadline task.
     * @throws InputException if the input format is incorrect or if the description is missing.
     */
    @Override
    public Task createTask(String input) throws InputException {
        if (input.equalsIgnoreCase("deadline")) {
            throw new InputException("To add a Deadline task, use the following format: deadline <task description>"
                    + " /by <DD/MM/YYYY HHmm>");
        }
        String[] details = input.split(" /by ");
        if (details.length == 2) {
            String description = details[0].trim();
            LocalDateTime by = parseDateTime(details[1].trim());
            if (description.isEmpty()) {
                throw new InputException("You need to describe your Deadline!");
            }
            return new Deadline(description, by);
        } else {
            throw new InputException("Invalid format. Use: deadline <description> /by <DD/MM/YYYY HHmm>");
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
     * Returns a string representation of the Deadline task.
     * The string includes a "[D]" prefix to indicate that it is a Deadline task,
     * followed by the task status, description, and due date.
     *
     * @return a string representing the Deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    /**
     * Returns a string representation of a Deadline when saving to file.
     *
     * @return a string representation of the Deadline.
     */
    @Override
    public String encode() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.format(formatter);
    }
}
