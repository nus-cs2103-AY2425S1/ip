package skywalker.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
* Represents a task with a deadline.
* A {@code Deadline} task has a description and a date by which it must be completed.
*/
public class Deadline extends Task {

    private LocalDate by;

    /**
     * Constructs a task with the specified description and due date.
     *
     * @param description The description of the task.
     * @param by The due date of the task, in the format "yyyy-MM-dd".
     */
    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.by = LocalDate.parse(by, formatter);
    }
    public LocalDate getBy() {
        return this.by;
    }

    /**
     * Returns a string representation of the deadline task.
     * The format is "[D][status] description (by: MMM dd yyyy)".
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }
}
