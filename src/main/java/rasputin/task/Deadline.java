package rasputin.task;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has a deadline to be completed by. A Deadline object has a deadline represented by
 * a string and a LocalDate or LocalDateTime.
 * If the deadline given is not the correct format, throws an InvalidTaskException.
 */
public class Deadline extends Task {

    String by;
    LocalDate date;
    LocalDateTime dateTime;
    boolean hasTime;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.hasTime = false;
        String[] split = by.split(" ");
        if (split.length == 1) {
            date = LocalDate.parse(split[0]);
        } else if (split.length == 2) {
            String str = split[0] + "T" + split[1].substring(0,2) + ":" + split[1].substring(2);
            dateTime = LocalDateTime.parse(str);
            this.hasTime = true;
        } else {
            throw new InvalidTaskException("ERROR! Invalid deadline format.");
        }
    }

    /**
     * Returns the string representation of the deadline.
     *
     * @return String representation of the deadline.
     */
    public String getBy() {
        return by;
    }

    @Override
    public String getType() {
        return "Deadline";
    }

    /**
     * Returns the string as the format to be saved in the .txt file.
     *
     * @return String in the format to be saved
     */
    @Override
    public String toSaveFormat() {
        String str = String.format("D|%s|%s|%s\n", getStatusIdentifier(), description, by);
        return str;
    }

    @Override
    public String toString() {
        String deadline;
        if (hasTime) {
            deadline = dateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy HHmm"));
        } else {
            deadline = date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        }
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }


}
