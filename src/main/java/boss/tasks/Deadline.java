package boss.tasks;

import java.time.format.DateTimeFormatter;

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
    }



    @Override
    public String toString() {
        return "[D]" + super.toString() + "| by: " + by;
    }
}
