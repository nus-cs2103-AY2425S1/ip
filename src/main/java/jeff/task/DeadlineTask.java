package jeff.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DeadlineTask extends Task {
    private LocalDateTime deadline;

    /**
     * Constructor to create a DeadlineTask object
     *
     * @param description that describes what the task is
     * @param deadline of the task
     */
    public DeadlineTask(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    public DeadlineTask(String description, LocalDateTime deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * String representation of the task
     *
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.getDateString(this.deadline, "MMM dd yyyy hh:mm a") + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + super.toFileString() + " | "
                + this.getDateString(this.deadline, "yyyy-MM-dd HH:mm");
    }

    @Override
    public boolean isOnThisDate(LocalDate date) {
        return date.equals(this.deadline.toLocalDate());
    }
}
