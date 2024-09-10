package bob.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a child class of Task
 * Deadline is a task which has a by field to indicate when the task has to be completed by
 */
public class Deadline extends Task {

    private final LocalDate deadlineDate;
    private LocalTime deadlineTime;

    /**
     * Constructor for Deadline when only a date is set
     *
     * @param taskName The name of the task that has to be completed
     * @param deadlineDate The date by which the task has to be completed by
     */
    public Deadline(String taskName, LocalDate deadlineDate) {
        super(taskName);
        this.deadlineDate = deadlineDate;
    }

    /**
     * Constructor for Deadline when a date and time is set
     *
     * @param taskName The name of the task that has to be completed
     * @param deadlineDate The date by which the task has to be completed by
     * @param deadlineTime The time by which the task has to be completed by
     */
    public Deadline(String taskName, LocalDate deadlineDate, LocalTime deadlineTime) {
        super(taskName);
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
    }

    /**
     * Exports the Deadline object to string to be saved in a text file
     *
     * @return String format of the Deadline object to be exported
     */
    @Override
    public String export() {
        return String.format("deadline %s /by %s%s", super.export(),
                this.deadlineDate.format(DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                this.deadlineTime != null ? " " + this.deadlineTime.format(DateTimeFormatter.ofPattern("HHmm")) : "");
    }

    /**
     * Converts the Deadline object to a string to be printed
     *
     * @return String format of the Deadline object for printing
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s%s)", "D", super.toString(),
                this.deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                this.deadlineTime != null ? " " + this.deadlineTime : "");
    }
}
