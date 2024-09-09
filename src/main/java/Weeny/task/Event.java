package weeny.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import weeny.parser.Parser;
/**
 * Represents an event task with a description, start, and end date/time.
 */
public class Event extends Task {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private Parser parser = new Parser();

    /**
     * Creates a new event task with a description, start date/time, and end date/time.
     *
     * @param description The task description.
     * @param start The start date and time in "yyyy-MM-dd HH:mm" format.
     * @param end The end date and time in "yyyy-MM-dd HH:mm" format.
     */
    public Event(String description, String start, String end) {
        super(description, TaskType.EVENT);
        String[] splitStart = start.split(" ");
        String[] splitEnd = end.split(" ");
        this.startDate = parser.convertDate(splitStart[0]);
        this.endDate = parser.convertDate(splitEnd[0]);
        this.startTime = parser.convertTime(splitStart[1]);
        this.endTime = parser.convertTime(splitEnd[1]);
    }

    /**
     * Returns a string representation of the event task for output purposes.
     *
     * @return The task in a format suitable for saving or displaying.
     */
    public String toOutput() {
        int checkMark = this.isDone ? 1 : 0;
        return "E | " + checkMark + " | " + this.description + " | "
                + this.startDate.format(DateTimeFormatter.ofPattern(READ_DATE_PATTERN)) + " "
                + this.startTime.format(DateTimeFormatter.ofPattern(READ_TIME_PATTERN)) + "-"
                + this.endDate.format(DateTimeFormatter.ofPattern(READ_DATE_PATTERN)) + " "
                + this.endTime.format(DateTimeFormatter.ofPattern(READ_TIME_PATTERN));
    }

    /**
     * Returns a string representation of the event task for display purposes.
     *
     * @return A formatted string showing the task's status, description, and start/end date/time.
     */
    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + getDescription()
                + " (from: " + this.startDate.format(DateTimeFormatter.ofPattern(WRITE_DATE_PATTERN)) + " "
                + this.startTime.format(DateTimeFormatter.ofPattern(WRITE_TIME_PATTERN))
                + " to: " + this.endDate.format(DateTimeFormatter.ofPattern(WRITE_DATE_PATTERN)) + " "
                + this.endTime.format(DateTimeFormatter.ofPattern(WRITE_TIME_PATTERN)) + ")";
    }
}
