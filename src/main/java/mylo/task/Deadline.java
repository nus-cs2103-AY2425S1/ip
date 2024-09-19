package mylo.task;

import java.time.LocalDateTime;

import mylo.utils.exceptions.IllegalValueException;
import mylo.utils.formatters.Formatter;
import mylo.utils.helpers.HelperFunctions;

/**
 * Represents a task with a deadline in the task list.
 * <p></p>
 * <p>The {@code Deadline} class stores the task title and its due date.
 * It extends the {@code Task} class and adds functionality specific to tasks that have deadlines.</p>
 * <p></p>
 * <p>The deadline is stored as a {@code LocalDateTime}, and the class provides methods to check
 * if the task is due on a specific date and to display or format the deadline.</p>
 *
 * @author cweijin
 */
public class Deadline extends Task {
    private final LocalDateTime deadline;

    /**
     * Constructs a {@code Deadline} task with the specified title and deadline.
     * <p></p>
     * <p>This constructor initializes the task with the title and deadline.
     * The task is considered incomplete upon creation.</p>
     *
     * @param title    The title or description of the deadline task.
     * @param deadline The deadline for the task in string format, which will be parsed into {@code LocalDateTime}.
     * @throws IllegalValueException If the provided deadline string cannot be parsed into a valid date and time.
     */
    public Deadline(String title, String deadline) throws IllegalValueException {
        this(title, deadline, false);
    }

    /**
     * Constructs a {@code Deadline} task with the specified title, deadline, and completion status.
     * <p></p>
     * <p>This constructor allows specifying whether the task is done at the time of creation.</p>
     *
     * @param title    The title or description of the deadline task.
     * @param deadline The deadline for the task in string format, which will be parsed into {@code LocalDateTime}.
     * @param isDone   Whether the task is marked as done upon creation.
     * @throws IllegalValueException If the provided deadline string cannot be parsed into a valid date and time.
     */
    public Deadline(String title, String deadline, boolean isDone) throws IllegalValueException {
        super(title, isDone);
        this.deadline = HelperFunctions.stringToDateTime(deadline);
    }

    /**
     * Checks if the task is due on the specified date.
     *
     * <p>This method compares the deadline date with the provided {@code LocalDateTime}
     * to determine if the task is due on the same day.</p>
     *
     * @param dateTime The date to check if the task is due on.
     * @return {@code true} if the task is due on the specified date, {@code false} otherwise.
     */
    public boolean isDueOnDate(LocalDateTime dateTime) {
        return deadline.toLocalDate().equals(dateTime.toLocalDate());
    }

    /**
     * Returns a formatted string representing the deadline task for storage.
     *
     * <p>This string is used for saving the task to storage and includes the task type, status,
     * title, and formatted deadline.</p>
     *
     * @return A string formatted for storage.
     */
    @Override
    public String storageFormat() {
        return String.format("DEADLINE | %s | %s | %s", super.completionStatus(), super.getTitle(),
                Formatter.dateTimeStorage(deadline));
    }

    /**
     * Returns a string representation of the deadline task, including its status and deadline.
     *
     * <p>The string includes the task type "[D]", the task's status (done or not),
     * the title, and the deadline formatted for display.</p>
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + getDeadlineString();
    }


    /**
     * Returns a formatted string of the deadline.
     *
     * <p>This private method formats the deadline for display purposes.</p>
     *
     * @return A string representing the formatted deadline.
     */
    private String getDeadlineString() {
        return " (by: " + Formatter.dateTimeDisplay(deadline) + ")";
    }

}
