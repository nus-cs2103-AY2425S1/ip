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
    public static void validateDescription(String command) throws OllieException {
        if (command.trim().equalsIgnoreCase("deadline")) {
            throw new EmptyDescriptionException("deadline");
        }

        if (!command.contains(" /by:")) {
            throw new OllieException("Please enter in the format:\n"
                    + "deadline <description> /by: <date>\n"
                    + "Example: deadline return book /by: 2021-09-30 18:00");
        }

        String description = command.substring(8, command.indexOf(" /by:")).trim();
        String deadline = command.substring(command.indexOf(" /by:") + 5).trim();

        // Handles Empty Description, e.g., "deadline /by: 2021-09-30 18:00"
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("deadline");
        }

        // Handles Missing Deadline, e.g., "deadline return book /by:"
        if (deadline.isEmpty()) {
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
        validateDescription(command);

        String[] parts = command.substring(8).split(" /by:");

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
