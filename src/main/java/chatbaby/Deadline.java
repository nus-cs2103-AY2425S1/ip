package chatbaby;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline in the ChatBaby application.
 * Extends the Task class to include a specific deadline for the task.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter INPUT_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter OUTPUT_FORMATTER =
            DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
    private LocalDateTime deadline;

    /**
     * Constructs a Deadline task with the specified name and deadline.
     *
     * @param name     The name of the task.
     * @param deadline The deadline of the task in the format "yyyy-MM-dd HH:mm".
     * @throws DateTimeParseException If the deadline is not in the correct format.
     */
    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = LocalDateTime.parse(deadline, INPUT_FORMATTER);
    }

    /**
     * Constructs a Deadline task with the specified name and deadline.
     *
     * @param name     The name of the task.
     * @param deadline The deadline of the task as a LocalDateTime object.
     */
    public Deadline(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * Returns the deadline of the task.
     *
     * @return The deadline as a LocalDateTime object.
     */
    public LocalDateTime getDeadline() {
        return deadline;
    }

    /**
     * Returns a string representation of the task suitable for saving to a file.
     *
     * @return A string representing the task for file storage.
     */
    @Override
    public String toFileText() {
        return "D | " + (this.isDone() ? "1" : "0") + " | " + this.getName() + " | "
                + deadline.format(OUTPUT_FORMATTER);
    }

    /**
     * Returns a string representation of the task suitable for displaying to the user.
     *
     * @return A string representing the task for display.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + deadline.format(OUTPUT_FORMATTER) + ")";
    }
}
