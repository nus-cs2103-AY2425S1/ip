package jeff.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task with a description and a due date.
 *
 * A Deadline object stores information about a task that has to be completed by a specific date and time.
 */
public class Deadline extends Task {
    private LocalDateTime dueDate;

    /**
     * Constructs a Deadline object with the specified description and due date.
     *
     * @param task Description of the task.
     * @param dueDate Date and time by which the task should be completed.
     */
    public Deadline(String task, LocalDateTime dueDate) {
        super(task);
        this.dueDate = dueDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public String dateToString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return dueDate.format(formatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dateToString() + ")";
    }

    @Override
    public String saveAsCSV() {
        return "D," + super.saveAsCSV() + "," + this.dateToString();
    }
}
