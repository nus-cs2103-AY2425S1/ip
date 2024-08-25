package terminator.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Concrete class representing a 'Deadline' item in the task list.
 */
public class DeadlineTask extends Task {

    private final LocalDateTime deadline;

    public DeadlineTask(String description, LocalDateTime deadline) {
        super(description, TaskType.DEADLINE);
        this.deadline = deadline;
    }

    /**
     * Returns the string format of the task.
     *
     * @example {@code "[D][ ] destroy aliens by: Jan 1 2000 09:00"}
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
        String byDate = deadline.format(formatter);
        return super.toString() + " by: " + byDate;
    }

    /**
     * Returns the data representation of the task, plus its deadline date and description.
     * @example {@code "D 1 2000-01-01+0900 destroy aliens"}
     */
    @Override
    public String getDataRepresentation() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd+HH:mm");
        return super.getDataRepresentation()
                + this.deadline.format(formatter) + " "
                + this.description;
    }
}
