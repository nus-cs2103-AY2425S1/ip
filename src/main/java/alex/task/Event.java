package alex.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an action to be done during a certain time period. An Event object corresponds to
 * a piece of work represented by a string e.g., read book from 1st september 2024 to 4th september 2024
 */
public class Event extends Task {
    public LocalDate startDate;
    //public LocalDate dueDate;
    public Event(String description, LocalDate startDate, LocalDate endDate) {
        super(description);
        this.startDate = startDate;
        this.dueDate = endDate;
    }
    /**
     * Returns a string that has the format to be saved in the file.
     *
     * @return string to be saved as data.
     */
    @Override
    public String toBeSavedAsData() {
        return "[E][" + super.getStatusIcon() + "] " + super.toString()
                + " // from: " + startDate + " // to: " + dueDate;
    }
    /**
     * Returns a string that has the format to be shown to the user.
     *
     * @return string to be displayed to users.
     */
    @Override
    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.toString()
                + " // from: " + startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " // to: " + dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
