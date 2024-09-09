package taskalyn;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline Task.
 */
public class DeadlineTask extends Task {
    private LocalDateTime deadline;

    /**
     * Constructs the DeadlineTask object with description, deadline, and completion status.
     *
     * @param taskItem Description of Deadline Task.
     * @param deadlineString Deadline of Deadline Task.
     * @param isCompleted Whether a Deadline Task is completed or not.
     */
    public DeadlineTask(String taskItem, String deadlineString, boolean isCompleted) {
        super(taskItem, isCompleted);
        this.deadline = getDeadlineDate(deadlineString);
    }

    /**
     * Creates a LocalDateTime object for the DeadlineTask.
     *
     * @param deadlineString String input of deadline.
     * @return LocalDateTime object of deadline.
     */
    private LocalDateTime getDeadlineDate(String deadlineString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(deadlineString, formatter);
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
        DateTimeFormatter databaseFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "D | " + (this.isCompleted() ? "1" : "0") + " | " + this.getTaskDescription() + " | "
                + this.deadline.format(databaseFormatter);
    }
}
