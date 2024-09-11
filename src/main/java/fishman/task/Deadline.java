package fishman.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task.
 * This class extends the Task class, adding a deadline attribute to it.
 */
public class Deadline extends Task {

    protected LocalDateTime deadlineDate;

    /**
     * Constructs a new Deadline task.
     *
     * @param taskDescription The description of the task.
     * @param deadlineDate The deadline in which the task should be completed by.
     */
    public Deadline(String taskDescription, boolean isTaskDone, LocalDateTime deadlineDate) {
        super(taskDescription, isTaskDone);
        assert taskDescription != null : "Description should not be null";
        assert deadlineDate != null : "Deadline 'by' date should not be null";
        this.deadlineDate = deadlineDate;
    }

    public LocalDateTime getDeadlineDate() {
        return deadlineDate;
    }
    @Override
    public String getTaskType() {
        return "D";
    }

    public void setDeadlineDate(LocalDateTime newDeadline) {
        this.deadlineDate = newDeadline;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");
        return super.toString() + " (by: " + deadlineDate.format(formatter) + ")";
    }
}
