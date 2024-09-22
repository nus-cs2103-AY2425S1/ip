package meowmeow;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task which has to be done within a certain period
 */
public class DoWithin extends Task {
    protected LocalDate periodStart;
    protected LocalDate periodEnd;

    /**
     * Constructs a DoWithin task with the specified description, start period
     * date and end period date.
     *
     * @param description The description of the task.
     * @param periodStart The start period of the task in the format "yyyy-MM-dd".
     * @param periodEnd The end period of the task in the format "yyyy-MM-dd".
     */
    public DoWithin(String description, String periodStart, String periodEnd) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.periodStart = LocalDate.parse(periodStart, formatter);
        this.periodEnd = LocalDate.parse(periodEnd, formatter);
    }

    /**
     * Returns a string representation of the DoWithin task, including the task type,
     * status, description, and start period and end period in the format "MMM dd yyyy".
     *
     * @return A string representation of the DoWithin task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[W]" + super.toString() + " (between: " + periodStart.format(formatter) + " and: " + periodEnd.format(formatter) + ")";
    }

    /**
     * Converts the DoWithin task into a format suitable for saving to a file.
     *
     * @return A string representing the DoWithin task in a file format.
     */
    @Override
    public String convertToFileFormat() {
        return "W | " + (isDone ? "1" : "0") + " | " + description + " | " + periodStart.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " | " + periodEnd.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

}
