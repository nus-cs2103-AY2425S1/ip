package boss.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Represents a Deadline, which is a type of task
 * users can add to their list.
 */
public class Deadline extends TimeTask {
    protected String by;

    /**
     * Creates a Deadline object.
     * @param description description of task
     * @param isDone status of task
     */

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;

        setDateTime(by);
    }

    /**
     * sets the LocalDate or LocalDateTime fields
     * @param by deadline of event
     */
    public void setDateTime(String by) {
        if (hasDateTime(by)) {
            this.time = formatDateTime(by);
            this.by = time.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm "));
        } else if (hasDate(by)) {
            this.date = formatDate(by);
            this.by = date.format(DateTimeFormatter.ofPattern("MMM d yyyy "));
        }

        // for date
        if (by.matches("[A-Za-z]{3} \\d{2} \\d{4} ") || by.matches("[A-Za-z]{3} \\d{1} \\d{4} ")) {
            DateTimeFormatter dTF = DateTimeFormatter.ofPattern("MMM d yyyy ");
            this.date = LocalDate.parse(by, dTF);
        }

        //for time
        if (by.matches("[A-Za-z]{3} \\d{2} \\d{4} \\d{2}:\\d{2} ") || by.matches("[A-Za-z]{3} \\d{1} \\d{4} \\d{2}:\\d{2} ")) {
            DateTimeFormatter dTF = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm ");
            this.time = LocalDateTime.parse(by, dTF);
        }
    }

    @Override
    public String getType() {
        return "deadline";
    }



    @Override
    public String toString() {
        return "[D]" + super.toString() + "| by: " + by;
    }
}
