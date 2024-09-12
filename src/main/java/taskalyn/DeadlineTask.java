package taskalyn;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline Task.
 */
public class DeadlineTask extends Task {
    private final LocalDateTime deadline;

    /**
     * Constructs the DeadlineTask object with description, deadline, and completion status.
     *
     * @param taskItem Description of Deadline Task.
     * @param deadlineDate Deadline of Deadline Task.
     * @param isCompleted Whether a Deadline Task is completed or not.
     */
    public DeadlineTask(String taskItem, LocalDateTime deadlineDate, boolean isCompleted) {
        super(taskItem, isCompleted);
        this.deadline = deadlineDate;
    }

    /**
     * Returns a String expression of the DeadlineTask.
     *
     * @return String expression of DeadlineTask.
     */
    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MM yyyy, h:mm a");
        return "[D]" + super.toString() + " (by: " + this.deadline.format(outputFormatter) + ")";
    }

    /**
     * Returns a String expression used in database file.
     *
     * @return String expression used in database file.
     */
    @Override
    public String toDatabaseFormat() {
        DateTimeFormatter databaseFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        return "D | " + (this.isCompleted() ? "1" : "0") + " | " + this.getTaskDescription() + " | "
                + this.deadline.format(databaseFormatter);
    }
}
