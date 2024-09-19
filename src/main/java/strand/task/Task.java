package strand.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

import strand.exception.StrandDescNotFoundException;
import strand.exception.StrandException;

/**
 * The {@code Task} class is an abstract representation of a task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected PriorityEnum priority = PriorityEnum.NONE;
    protected DateTimeFormatter formatterWithTime = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")) // Handles 'T' separator
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"))
            .toFormatter();
    protected DateTimeFormatter formatterWithoutTime = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            .appendOptional(DateTimeFormatter.ofPattern("d/MM/yyyy"))
            .toFormatter();
    protected DateTimeFormatter formatterDisplay = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");

    /**
     * Constructs a new {@code Task} with the specified description.
     * By default, the task is not marked as done.
     *
     * @param description The description of the task.
     * @throws StrandException if the description is empty.
     */
    public Task(String description) throws StrandException {
        if (description.isEmpty()) {
            throw new StrandDescNotFoundException("Description");
        }
        this.description = description;
        this.isDone = false;
    }

    /**
     * Parses a date string and returns a {@code LocalDateTime} object.
     * <p>
     * The method tries to parse the date using multiple formats, both with and
     * without time information. If parsing as {@code LocalDateTime} fails, it
     * attempts to parse as {@code LocalDate} and returns the start of the day
     * as the {@code LocalDateTime}.
     * </p>
     *
     * @param date The date string to be parsed.
     * @return The parsed {@code LocalDateTime} object.
     * @throws DateTimeParseException if the date cannot be parsed.
     */
    public LocalDateTime parseDate(String date) throws DateTimeParseException {
        try {
            return LocalDateTime.parse(date, formatterWithTime);
        } catch (DateTimeParseException e) {
            LocalDate localDate = LocalDate.parse(date, formatterWithoutTime);
            return localDate.atStartOfDay();
        }
    }

    /**
     * Formats a {@code LocalDateTime} object into a string for display purposes.
     *
     * @param date The {@code LocalDateTime} object to be formatted.
     * @return The formatted date string.
     */
    public String parseOutputDate(LocalDateTime date) {
        return date.format(formatterDisplay);
    }

    /**
     * Returns the status of the task as a string, indicating whether it is done.
     *
     * @return A string representing the status of the task.
     */
    private String getStatus() {
        return "[" + (isDone ? "X" : " ") + "] ";
    }

    /**
     * Returns the type of the task.
     * <p>
     * This method is abstract and must be implemented by subclasses.
     * </p>
     *
     * @return A string representing the type of the task.
     */
    abstract String getType();

    /**
     * Returns the string representation of the task, including its status and description.
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return String.format("%s%s%s",
                this.getStatus(),
                this.description,
                this.priority != PriorityEnum.NONE ? " #PRIORITY:" + this.priority : "");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Marks the task's priority.
     */
    public void markPriority(PriorityEnum priority) {
        this.priority = priority;
    }

    /**
     * Returns the string representation of the task for file storage,
     * including its status and description.
     *
     * @return A string formatted for file storage.
     */
    public String convertToFileFormat() {
        return String.format("%s | %s | %s",
                this.isDone ? "1" : "0", this.description, this.priority);
    }

    public Boolean containsSegment(String segment) {
        return this.description.toLowerCase().contains(segment.toLowerCase());
    }

    /**
     * Enumeration of possible priorities.
     */
    public enum PriorityEnum {
        HIGH,
        MEDIUM,
        LOW,
        NONE,
    }
}
