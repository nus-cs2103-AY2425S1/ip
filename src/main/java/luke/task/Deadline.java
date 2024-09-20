package luke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The {@code Deadline} class represents the Deadline task type. The Deadline task contains a task name,
 *
 * <p>
 * The task description is shown in the format:
 * <pre>
 * [D][X] Task Name (by: Deadline Date)
 * </pre>
 * where the "D" represents a "Deadline" task, and the "X" (or blank) represents the completion status.
 * </p>
 * The save format for a Deadline task is as follows:
 * <pre>
 * X | deadline | Task Name by Deadline Date
 * </pre>
 * where "X" represents that the task is marked as done, and "-" indicates it is not done.
 * </p>
 *
 * @see Task
 * @see Todo
 * @see Event
 */
public class Deadline extends Task {
    protected static final DateTimeFormatter DATE_TIME_INPUT_FORMATTER =
            DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm");
    protected static final DateTimeFormatter DATE_TIME_OUTPUT_FORMATTER =
            DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm");
    protected String deadline;

    /**
     * Constructs a new {@code Deadline} task with the specified task name, deadline, and completion status.
     *
     * @param taskName The name of the deadline task.
     * @param deadline The due date and time for the task.
     * @param isDone   The completion status of the deadline task. {@code true} if the task is completed.
     */
    public Deadline(String taskName, String deadline, boolean isDone) {
        super(taskName, isDone);
        if (deadline.charAt(0) == '0') {
            deadline = deadline.substring(1, deadline.length() - 1);
        }
        try {
            LocalDateTime parsedDateInput = LocalDateTime.parse(deadline, DATE_TIME_INPUT_FORMATTER);
            this.deadline = parsedDateInput.format(DATE_TIME_OUTPUT_FORMATTER);
        } catch (java.time.format.DateTimeParseException e) {
            // assuming the input is already formatted in the output format
            this.deadline = deadline;
        }
    }

    @Override
    public String taskDescription() {
        return "[D]" + showMark() + " " + this.name
                + " (by: " + deadline + ")";
    }

    @Override
    public String taskInSaveData() {
        return (isDone ? "X" : "-")
                + " | deadline | "
                + name + " by " + deadline + "\n";
    }
}
