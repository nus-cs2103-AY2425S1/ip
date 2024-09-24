package task;
import exception.InvalidDeadlineException;
import prince.Prince;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task for an event.
 *
 * A Eventtask is a type of task that includes a start time and end time
 * The class provides multiple methods to retrieve and save that information.
 */

public class EventTask extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    protected static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
    protected static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");


    /**
     * Constructs a EventTask with the specified description and deadline in a particular format
     * @param description
     * @param start
     * @param end
     */
    public EventTask(String description, String start, String end) throws InvalidDeadlineException{
        super(description);
        DateTimeFormatter toLocalDateTimeF = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            this.start = LocalDateTime.parse(start, toLocalDateTimeF);
            this.end = LocalDateTime.parse(end, toLocalDateTimeF);
        } catch (DateTimeParseException e) {
            throw new InvalidDeadlineException("Error saving task. You have given an invalid date-time format.\n" +
                    "Please use this format, yyyy-MM-dd HHmm.\n" + "An example is 2024-10-15 1800");
        }

    }

    public LocalDateTime getStart() {
        return this.start;
    }

    public LocalDateTime getEnd() {
        return this.end;
    }

    private String getDateTimeToString(LocalDateTime time) {
        if (time == null) {
            return "No time set";
        }

        LocalTime timeChecker = LocalDateTime.of(0, 1, 1, 0, 0).toLocalTime();
        if (time.toLocalTime().equals(timeChecker)) {
            return time.format(dateFormatter);
        } else {
            return time.format(dateTimeFormatter);
        }
    }


    /**
     * Returns a string representation of the task in a human-readable format.
     *
     * @return String
     */
    @Override
    public String printTask() {
        return "[E]" + super.printTask() +  " (from: " + getDateTimeToString(this.start) + " to: " +
                getDateTimeToString(this.end) + ")";
    }

    /**
     * Returns a string representation of the task in a file-storage format.
     *
     * @return String
     */
    @Override
    public String printFileFormat() {
        return "E | " + (isDone ? 1 : 0) + " | " + description + " | " + getDateTimeToString(this.start) + " | " +
                getDateTimeToString(this.end);
    }
}
