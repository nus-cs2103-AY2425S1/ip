package ollie.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ollie.exception.EmptyDescriptionException;
import ollie.exception.OllieException;

/**
 * Deadline is a type of Task.
 * It represents a task with a description and a due date.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Constructs a Deadline task with the specified description and due date.
     *
     * @param description The description of the Deadline task.
     * @param deadline The due date of the Deadline task.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description, TaskType.DEADLINE);
        assert description != null : "Oops! Description cannot be null.";
        assert deadline != null : "Oops! Deadline cannot be null.";
        this.deadline = deadline;
    }

    /**
     * Validates the description of the Deadline task by throwing an exception for incomplete commands.
     *
     * @param command The command entered by the user.
     * @throws OllieException If the description is empty or the deadline is not provided.
     */
    @Override
    public void validateDescription(String command) throws OllieException {
        String[] parts = command.split(" /by ");
        if (parts.length != 2) {
            throw new OllieException("Please enter a name and deadline for the task! ☺");
        }
        if (parts[0].trim().isEmpty()) {
            throw new EmptyDescriptionException("deadline");
        }
        if (parts[1].trim().isEmpty()) {
            throw new OllieException("Please enter a deadline for the task! ☺");
        }
    }

    /**
     * Creates a Deadline task from the specified command.
     *
     * @param command The command entered by the user.
     * @return The Deadline task created from the command.
     * @throws OllieException If the command is in the wrong format or the date is invalid.
     */
    public static Deadline createTask(String command) throws OllieException {
        String[] parts = command.substring(8).split(" /by:");

        if (parts.length != 2) {
            throw new OllieException("""
                    Please enter in the format:
                    deadline task_name /by: due_date
                    Example: deadline assignment /by: 2021-09-30 23:59""");
        }

        DateTimeFormatter inputDate = Task.getInputDate();

        try {
            return new Deadline(parts[0].trim(), LocalDateTime.parse(parts[1].trim(), inputDate));
        } catch (Exception e) {
            throw new OllieException("Please enter the date in the format: yyyy-MM-dd HH:mm");
        }
    }

    /**
     * Returns the due date of the Deadline task.
     *
     * @return deadline of the Deadline task.
     */
    public LocalDateTime getDeadline() {
        return deadline;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String saveAsString() {
        DateTimeFormatter formatDate = Task.getFormatDate();
        return String.format("%s | %s", super.saveAsString(), deadline.format(formatDate));
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        DateTimeFormatter formatDate = Task.getFormatDate();
        return super.toString() + " (by: " + deadline.format(formatDate) + ")";
    }
}
