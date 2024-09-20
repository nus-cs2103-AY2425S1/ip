package tissue.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/** Wrapper for a deadline task. */
public class Deadline extends Task {

    private LocalDate by;

    /**
     * Constructor for creating deadline with boolean value
     *
     * @param isDone True if task is done. False otherwise.
     * @param task The task to be stored.
     * @param by The deadline of the task.
     * @throws DateTimeParseException If format of date is wrong.
     */
    public Deadline(boolean isDone, String task, String by) throws DateTimeParseException {
        super(isDone, task);
        this.by = LocalDate.parse(by);
    }

    /**
     * Constructor for creating deadline with int value
     *
     * @param isDone isDone = 1 if a task is done. 0 otherwise.
     * @param task The task to be stored.
     * @param by The deadline of the task.
     * @throws DateTimeParseException If format of date is wrong.
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
