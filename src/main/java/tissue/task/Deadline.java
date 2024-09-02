package tissue.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Wrapper for a deadline task.
 */
public class Deadline extends Task {

    private LocalDate by;

    /**
     * Constructor for accepting boolean isDone value.
     */
    public Deadline(boolean isDone, String task, String by) throws DateTimeParseException {
        super(isDone, task);
        this.by = LocalDate.parse(by);
    }

    /**
     * Constructor for accepting int isDone value.
     */
    public Deadline(int isDone, String task, String by) throws DateTimeParseException {
        super(isDone == 1, task);
        this.by = LocalDate.parse(by);
    }

    public LocalDate getBy() {
        return this.by;
    }

    public void setBy(String by) throws DateTimeParseException {

        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {

        String task =
                super.getTask()
                        + " "
                        + String.format(
                                "(by: %s)", by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
        return super.getIsDone() ? "[D][X] " + task : "[D][ ] " + task;
    }
}
