package bimo.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Creates a Task with deadline.
 */
public class Deadline extends Task {
    private LocalDate dueDate;

    /**
     * Instantiates a Deadline object.
     *
     * @param details Description of task.
     * @param dueDate Deadline of task.
     */
    public Deadline(String details, LocalDate dueDate) {
        super(details);
        this.dueDate = dueDate;
    }

    /**
     * Formats end date to write into file.
     *
     * @return End date as text with |.
     */
    public String getDateAsText() {
        String dueDateAsText = "|" + this.dueDate.toString();
        return dueDateAsText;
    }


    /**
     * Converts task to string value with task type, description
     * and due date.
     *
     * @return String value of task.
     */
    @Override
    public String toString() {
        String deadlineString = String.format("[D]%s (by: %s)", super.toString(),
                this.dueDate.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
        return deadlineString;
    }
}
