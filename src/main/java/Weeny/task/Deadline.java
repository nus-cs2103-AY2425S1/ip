package weeny.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import weeny.parser.Parser;
/**
 * Represents a deadline task with a description and due date/time.
 */
public class Deadline extends Task {
    private final LocalDate endDate;
    private final LocalTime endTime;
    private Parser parser = new Parser();

    /**
     * Creates a new deadline task with a description and due date/time.
     *
     * @param description The task description.
     * @param date The due date and time in "yyyy-MM-dd HH:mm" format.
     */
    public Deadline(String description, String date) {
        super(description, TaskType.DEADLINE);
        String[] splitDate = date.split(" ");
        this.endDate = parser.convertDate(splitDate[0]);
        this.endTime = parser.convertTime(splitDate[1]);
    }

    /**
     * Returns a string representation of the deadline task for output purposes.
     *
     * @return The task in a format suitable for saving or displaying.
     */
    public String toOutput() {
        int checkMark = this.isDone ? 1 : 0;
        return "D | " + checkMark + " | " + this.description + " | "
                + this.endDate.format(DateTimeFormatter.ofPattern(READ_DATE_PATTERN)) + " "
                + this.endTime.format(DateTimeFormatter.ofPattern(READ_TIME_PATTERN));
    }

    /**
     * Returns a string representation of the deadline task for display purposes.
     *
     * @return A formatted string showing the task's status, description, and due date/time.
     */
    @Override
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "] " + getDescription()
                + " (by: " + this.endDate.format(DateTimeFormatter.ofPattern(WRITE_DATE_PATTERN)) + " "
                + this.endTime.format(DateTimeFormatter.ofPattern(WRITE_TIME_PATTERN)) + ")";
    }
}
