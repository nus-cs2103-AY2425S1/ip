package xbot.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a task with a deadline in the XBot application.
 * A {@code Deadline} task has a description and a deadline by which it should be completed.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructs a new {@code Deadline} task with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param by The deadline by which the task should be completed.
     */
    public Deadline(String description, String by) {
        super(description, TaskType.D);
        this.by = by;
    }

    /**
     * Returns the deadline of the task.
     *
     * @return The deadline by which the task should be completed as a String.
     */
    public String getBy() {
        return by;
    }

    /**
     * Converts the deadline to a different date format.
     * The method attempts to parse the deadline using multiple date formats.
     * If successful, it returns the date in a standard format; otherwise, it returns an error message.
     *
     * @param by The original deadline string to be converted.
     * @return The formatted deadline string, or an error message if the conversion fails.
     */
    public String changeDateFormat(String by) {

        List<String> formats = new ArrayList<>();
        formats.add("yyyy-MM-dd");
        formats.add("d/M/yyyy");
        formats.add("d/M/yyyy HHmm");

        for (String format : formats) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            try {
                if (format.contains("HHmm")) {
                    LocalDateTime dateTime = LocalDateTime.parse(by, formatter);
                    return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma"));
                } else {
                    LocalDate date = LocalDate.parse(by, formatter);
                    return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                }
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        return "TimeDate cannot be converted to another format :'0";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + changeDateFormat(by) + ")";
    }
}
