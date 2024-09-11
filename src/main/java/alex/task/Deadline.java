package alex.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an action to be done by a certain date. A Deadline object corresponds to
 * a piece of work represented by a string e.g., read book by Sunday
 */
public class Deadline extends Task {

    public Deadline(String description, LocalDate dueDate) {
        super(description);
        this.dueDate = dueDate;
    }
    /**
     * Returns a string that has the format to be saved in the file.
     *
     * @return string to be saved as data.
     */
    @Override
    public String toBeSavedAsData() {
        return "[D][" + getStatusIcon() + "] " + super.toString() + " // by: " + dueDate;
    }
    /**
     * Returns a string that has the format to be shown to the user.
     *
     * @return string to be displayed to users.
     */
    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + super.toString() + " // by: " + dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) ;
    }
}
