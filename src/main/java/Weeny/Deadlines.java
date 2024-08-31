package weeny;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a description and due date/time.
 */
public class Deadlines extends Task {
    private LocalDate endDate;
    private LocalTime endTime;
    private Parser parser = new Parser();

    /**
     * Creates a new deadline task with a description and due date/time.
     *
     * @param description The task description.
     * @param date The due date and time in "yyyy-MM-dd HH:mm" format.
     */
    public Deadlines(String description, String date) {
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
        return "D | " + checkMark + " | " + this.description + " | " +
                this.endDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " " +
                this.endTime.format(DateTimeFormatter.ofPattern("HHmm"));
    }

    /**
     * Returns a string representation of the deadline task for display purposes.
     *
     * @return A formatted string showing the task's status, description, and due date/time.
     */
    @Override
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "] " + getDescription() +
                " (by: " + this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " +
                this.endTime.format(DateTimeFormatter.ofPattern("h:mm a")) + ")";
    }
}
