package ollie.task;

import ollie.exception.EmptyDescriptionException;
import ollie.exception.OllieException;
import ollie.TaskType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a ollie.task.Deadline task with a description and a due date.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Constructs a ollie.task.Deadline task with the specified description and due date.
     *
     * @param description The description of the ollie.task.Deadline task.
     * @param deadline The due date of the ollie.task.Deadline task.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description, TaskType.DEADLINE);
        this.deadline = deadline;
    }

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

    public static Deadline createTask(String command) throws OllieException {
        String[] parts = command.substring(8).split(" /by:");
        if (parts.length != 2) {
            throw new OllieException("Please enter in the format:\n" +
                    "deadline task_name /by: due_date" +
                    "\nExample: deadline assignment /by: 2021-09-30 23:59");
        }
        DateTimeFormatter inputDate = Task.getInputDate();
        try {
            return new Deadline(parts[0].trim(), LocalDateTime.parse(parts[1].trim(), inputDate));
        } catch (Exception e) {
            throw new OllieException("Please enter the date in the format: yyyy-MM-dd HH:mm");
        }
    }

    @Override
    public String saveAsString() {
        DateTimeFormatter formatDate = Task.getFormatDate();
        return String.format("%s | %s", super.saveAsString(), deadline.format(formatDate));
    }

    @Override
    public String toString() {
        DateTimeFormatter formatDate = Task.getFormatDate();
        return super.toString() + " (by: " + deadline.format(formatDate) + ")";
    }
}