package elara.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific deadline.
 */
public class DeadlineTask extends Task {
    protected LocalDateTime deadline;

    /**
     * Constructs a new DeadlineTask with the specified description, deadline, and completion status.
     *
     * @param desc The description of the task.
     * @param deadline The deadline for the task, represented as a LocalDateTime object.
     * @param isDone The completion status of the task (true if the task is completed, false otherwise).
     */
    public DeadlineTask(String desc, LocalDateTime deadline, boolean isDone) {
        super(desc, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
        return String.format("[D]%s (by: %s)", super.toString(), deadline.format(formatter));
    }

    /**
     * Returns the string format of the deadline task to be saved in a file.
     * The format includes the task type ("D"), the completion status (1 for done, 0 for not done),
     * the task description, and the deadline in ISO format (yyyy-MM-dd'T'HH:mm).
     *
     * @return A string representation of the deadline task in file format.
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + deadline;
    }
}
