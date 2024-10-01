package garfield.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * The Deadline class represents a task with a specific deadline in the Garfield chatbot application.
 * It extends the Task class to provide a representation for tasks that have a deadline.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;
    private DateTimeFormatter saveFormatter;
    private DateTimeFormatter uiFormatter;

    /**
     * Constructs a new Deadline task with the specified description and deadline.
     *
     * @param taskDescription The description of the deadline task.
     * @param deadline The deadline of the task, represented as a LocalDateTime object.
     */
    public Deadline(String taskDescription, LocalDateTime deadline) {
        super(taskDescription);
        this.deadline = deadline;
        this.saveFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.US);
        this.uiFormatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mma", Locale.US);
    }

    /**
     * Returns a string representation of the deadline task suitable for display to the user.
     * The representation includes a "[D]" prefix to indicate that it is a deadline task,
     * along with the completion status, task description, and formatted deadline.
     *
     * @return A string representation of the deadline task, with "[D]" indicating the task type
     *         and the deadline formatted for user interface display.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.format(uiFormatter) + ")";
    }

    /**
     * Returns a string representation of the deadline task suitable for saving to storage.
     * The representation includes a "D | " prefix to indicate that it is a deadline task,
     * followed by the completion status, task description, and formatted deadline.
     *
     * @return A string representation of the deadline task, with "D | " indicating the task type
     *         and the deadline formatted for saving.
     */
    @Override
    public String toSaveRepresentation() {
        return "D | " + super.toSaveRepresentation() + " | " + deadline.format(saveFormatter);
    }
}
