package edith.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents a Deadline task, which is a task that needs to be completed by a specific date and time.
 * This class extends the abstract Task class.
 */
public class Deadline extends Task {
    private LocalDateTime dueDate;
    private String taskStringToSave;

    /**
     * Constructs a new Deadline task with the specified description and due date.
     *
     * @param task           The description of the Deadline task.
     * @param dueDateString  The due date of the task as a string in the format "d/M/yyyy HHmm".
     */
    public Deadline(String task, String dueDateString) {
        super("[D] ", task);
        this.dueDate = parseDueDate(dueDateString);
        this.taskStringToSave = task + " /by " + dueDateString;
    }

    /**
     * Parses a string representing a date and time into a LocalDateTime object.
     *
     * @param dueDateString The due date string in the format "d/M/yyyy HHmm".
     * @return The parsed LocalDateTime object.
     */
    private LocalDateTime parseDueDate(String dueDateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return LocalDateTime.parse(dueDateString, formatter);
    }

    /**
     * Returns the due date of the task as a LocalDate object.
     *
     * @return The due date as a LocalDate.
     */
    public LocalDate getDueDate() {
        return this.dueDate.toLocalDate();
    }

    /**
     * Returns the string representation of the task to be saved to storage.
     *
     * @return The string to be saved.
     */
    @Override
    public String savedTaskString() {
        return taskStringToSave;
    }

    /**
     * Returns the string representation of the Deadline task.
     *
     * @return A string describing the Deadline task, including its status, description, and due date.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, d MMM yyyy, ha", Locale.ENGLISH);
        String formattedDueDate = this.dueDate.format(formatter);
        String string = " (due: " + formattedDueDate + ")";
        return typeOfTaskString() + statusString() + taskString() + string;
    }
}
