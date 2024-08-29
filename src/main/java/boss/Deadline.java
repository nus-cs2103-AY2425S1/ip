package boss;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline, which is a type of task
 * users can add to their list.
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDateTime dateTime;
    protected LocalDate date;

    /**
     * Creates a Deadline object.
     * @param description description of task
     * @param isDone status of task
     * @param by deadline date
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Creates a Deadline object.
     * @param description description of task
     * @param isDone status of task
     * @param date deadline date, stored as a LocalDateTime
     */
    public Deadline(String description, boolean isDone, LocalDateTime date) {
        super(description, isDone);
        this.by = date.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
        this.dateTime = date;
    }

    /**
     * Creates a Deadline object.
     * @param description description of task
     * @param isDone status of task
     * @param date deadline date, stored as a LocalDate
     */
    public Deadline(String description, boolean isDone, LocalDate date) {
        super(description, isDone);
        this.by = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        this.date = date;
    }


    /**
     * Creates a Deadline object using one of the constructors from above.
     * @param description description of task
     * @param by deadline date
     * @param isDone status of task
     */
    public static Deadline of(String description, String by, boolean isDone) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime date = LocalDateTime.parse(by, formatter);
            return new Deadline(description, isDone, date);
        } catch (DateTimeParseException e) {
            try {
                LocalDate date = LocalDate.parse(by);
                return new Deadline(description, isDone, date);
            } catch (DateTimeParseException e2) {
                return new Deadline(description, isDone, by);
            }
        }
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + "| by: " + by;
    }
}
