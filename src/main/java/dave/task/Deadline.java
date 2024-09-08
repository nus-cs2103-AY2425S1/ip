package dave.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import dave.exceptions.InvalidDateTimeFormatException;
import dave.exceptions.InvalidDescriptionException;

/**
 * Represents a deadline task. A deadline task contains a description, a due date, and a due time.
 * It inherits from the Task class.
 */
public class Deadline extends Task {

    /** The due date of the task */
    private LocalDate dueDate;

    /** The due time of the task */
    private LocalTime dueTime;

    /**
     * Constructs a Deadline task with the specified description and due date/time.
     *
     * @param description The description of the deadline task, which includes the due date and time.
     * @throws InvalidDescriptionException If the format of the description is invalid.
     * @throws InvalidDateTimeFormatException If the date or time is not in the required format.
     */
    public Deadline(String description) throws InvalidDescriptionException, InvalidDateTimeFormatException {
        super(description.split("/by ")[0].trim());
        String[] arguments = description.split("/by ");

        if (arguments.length < 2 || arguments[1].trim().isEmpty()) {
            throw new InvalidDescriptionException("Huh! Please provide a deadline task "
                    + "in the format: <task> /by <date>");
        }

        String[] due = arguments[1].trim().split(" ");
        this.dueDate = parseDate(due[0]);
        this.dueTime = due.length > 1 ? parseTime(due[1]) : null;
    }

    /**
     * Parses the provided date string into a LocalDate.
     *
     * @param date The date string in yyyy-MM-dd format.
     * @return The parsed LocalDate.
     * @throws InvalidDateTimeFormatException If the date is in an incorrect format.
     */
    private LocalDate parseDate(String date) throws InvalidDateTimeFormatException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeFormatException("Invalid date format. Please use yyyy-mm-dd");
        }
    }

    /**
     * Parses the provided time string into a LocalTime.
     *
     * @param time The time string in HHmm format.
     * @return The parsed LocalTime.
     * @throws InvalidDateTimeFormatException If the time is in an incorrect format.
     */
    private LocalTime parseTime(String time) throws InvalidDateTimeFormatException {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        try {
            return LocalTime.parse(time, timeFormatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeFormatException("Invalid time format. Please use HHmm (e.g., 1800 for 6:00 PM)");
        }
    }

    /**
     * Returns the due date of the task.
     *
     * @return The due date as a {@code LocalDate}.
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * Returns the due time of the task.
     *
     * @return The due time as a {@code LocalTime}.
     */
    public LocalTime getDueTime() {
        return dueTime;
    }
    /**
     * Converts the deadline into a string format that is suitable for saving to a file.
     *
     * @return The formatted string representing the deadline task.
     */
    @Override
    public String write() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        String formattedDate = dueDate != null ? dueDate.format(dateFormatter) : "unknown date";
        String formattedTime = dueTime != null ? dueTime.format(timeFormatter) : "no specific time";
        return String.format("D | %d | %s | %s %s\n", this.getIsDone() ? 1 : 0,
                this.getDescription(), formattedDate, formattedTime);
    }

    /**
     * Returns the string representation of the deadline task for display purposes.
     *
     * @return The string representation of the deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String formattedDate = dueDate != null ? dueDate.format(dateFormatter) : "unknown date";
        String formattedTime = dueTime != null ? dueTime.format(timeFormatter) : "";
        return "[D]" + super.toString() + " (by: " + formattedDate + (dueTime != null ? " " + formattedTime : "") + ")";
    }
}
