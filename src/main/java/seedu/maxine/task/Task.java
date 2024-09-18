package seedu.maxine.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import seedu.maxine.exception.MaxineException;
/**
 * Represents a task with a description and a completion status.
 * <p>
 * The task can be marked as done or undone. This class provides methods to
 * handle task descriptions, status, and date/time parsing.
 * </p>
 *
 * <p>
 * The date and time can be parsed from various formats using the
 * {@link #dateTimeParser(String)} method.
 * </p>
 */
public class Task {
    private static int count;
    private static final List<DateTimeFormatter> FORMATS = new ArrayList<>();
    protected String description;
    protected boolean isDone;
    protected int num;

    static {
        FORMATS.add(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        FORMATS.add(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        FORMATS.add(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        FORMATS.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        FORMATS.add(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }


    /**
     * Constructs a new task with a null description and a default status of not done.
     * <p>
     * The task number is automatically incremented with each new instance.
     * </p>
     */
    public Task() {
        this.description = null;
        this.isDone = false;
        count += 1;
        this.num = count;
    }


    /**
     * Constructs a new task with the specified description and a default status of not done.
     * <p>
     * The task number is automatically incremented with each new instance.
     * The description is converted to lowercase.
     * </p>
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description.toLowerCase();
        this.isDone = false;
        count += 1;
        this.num = count;
    }


    /**
     * Toggles the status of the task between done and not done.
     */
    public void markDone() {
        this.isDone = true;
    }
    public void markUndone() {
        this.isDone = false;
    }


    /**
     * Returns the status icon of the task.
     * <p>
     * Returns "[X] " if the task is done, otherwise "[ ] ".
     * </p>
     *
     * @return A string representing the status icon of the task.
     */
    public String getStatusIcon() {
        return isDone ? "[X] " : "[ ] "; // mark done maxine.task with X
    }

    /**
     * Returns a string representation of the task in a format suitable for file storage.
     * <p>
     * The format includes the status and description of the task, separated by slashes.
     * </p>
     *
     * @return A string representation of the task suitable for file storage.
     */
    public String writeToFile() {
        return " / " + (isDone ? 1 : 0) + " / " + description;
    }


    /**
     * Returns a string representation of the task in a human-readable format.
     * <p>
     * The format includes the status icon and the description of the task.
     * </p>
     *
     * @return A string representation of the task formatted as "[status] description".
     */
    @Override
    public String toString() {
        return getStatusIcon() + description;
    }


    /**
     * Parses a date and time string into a formatted string based on known formats.
     * <p>
     * The method tries to parse the input string using multiple date and time formats.
     * If successful, the date is formatted into a more readable format.
     * </p>
     *
     * @param dateTime The date and time string to parse.
     * @return A formatted date string.
     * @throws MaxineException If the input string does not match any of the known formats.
     */
    public String dateTimeParser(String dateTime) throws MaxineException {
        for (DateTimeFormatter formatter : FORMATS) {
            try {
                // Check if the formatter pattern includes time
                if (formatter.toString().contains("HH:mm")) {
                    // Try parsing as LocalDateTime
                    LocalDateTime deadline = LocalDateTime.parse(dateTime, formatter);
                    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
                    return deadline.format(outputFormatter);
                } else {
                    // Try parsing as LocalDate
                    LocalDate deadline = LocalDate.parse(dateTime, formatter);
                    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
                    return deadline.format(outputFormatter);
                }
            } catch (DateTimeParseException e) {
                // Continue checking with other formats
            }
        }
        throw new MaxineException("Date time format not found");
    }
}
