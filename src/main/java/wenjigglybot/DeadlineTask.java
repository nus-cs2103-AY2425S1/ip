package wenjigglybot;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * A subclass of Task representing a task with a deadline.
 * This class can handle both string-based deadlines and LocalDate deadlines.
 */
public class DeadlineTask extends Task {

    /**
     * Stores the deadline as a string if not provided as a LocalDate.
     */
    private String deadlineString;

    /**
     * Stores the deadline as a LocalDate for proper date formatting and manipulation.
     */
    private LocalDate deadline;

    /**
     * Constructs a DeadlineTask with a task description and a deadline as a LocalDate.
     *
     * @param description The description of the task.
     * @param deadline    The deadline as a LocalDate.
     */
    public DeadlineTask(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Constructs a DeadlineTask with a task description and a deadline as a string.
     *
     * @param taskDescription The description of the task.
     * @param deadline        The deadline as a string.
     */
    public DeadlineTask(String taskDescription, String deadline) {
        super(taskDescription);
        this.deadlineString = deadline;
    }

    /**
     * Returns the type of the task, which is "Deadline Task" in this case.
     *
     * @return A string representing the task type.
     */
    @Override
    public String taskType() {
        return "Deadline Task";
    }

    /**
     * Returns the string representation of the DeadlineTask, including its type and formatted deadline.
     * If the deadline is a LocalDate, it formats the date accordingly.
     *
     * @return A string representation of the DeadlineTask with the deadline.
     */
    @Override
    public String toString() {
        String formattedDeadline = String.format("(by: %s)",
                deadline == null ? deadlineString : deadline.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
        return "[D]" + super.toString() + " " + formattedDeadline;
    }
}