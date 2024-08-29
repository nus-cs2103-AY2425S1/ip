package mgtow.task;

import mgtow.util.InvalidTaskException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific deadline in the Mgtow application.
 * This class extends the Task class and adds functionality for managing deadlines.
 */
public class Deadline extends Task {
    private LocalDateTime end;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");

    /**
     * Constructs a new Deadline task.
     *
     * @param desc The description of the deadline task.
     * @param end The end date and time of the deadline in the format "yyyy-MM-dd HHmm".
     * @throws InvalidTaskException If the date format is invalid.
     */
    public Deadline(String desc, String end) throws InvalidTaskException {
        super(desc, "D");
        try {
            this.end = LocalDateTime.parse(end, INPUT_FORMAT);
        } catch (Exception e) {
            throw new InvalidTaskException("Invalid date format. Use yyyy-MM-dd HHmm");
        }
    }

    /**
     * Gets the end date and time of the deadline.
     *
     * @return The LocalDateTime object representing the end of the deadline.
     */
    public LocalDateTime getEnd() {
        return end;
    }

    public String getDeadline() {
        return end.format(INPUT_FORMAT);
    }

    @Override
    public String toString(){
        return super.toString() + "(by: " + end.format(OUTPUT_FORMAT) + ")";
    }

    /**
     * Checks if the deadline is on the given date.
     *
     * @param date The date to check.
     * @return true if the deadline is on the given date, false otherwise.
     */
    @Override
    public boolean isOnDate(LocalDate date) {
        return getEnd().toLocalDate().equals(date);
    }


}
