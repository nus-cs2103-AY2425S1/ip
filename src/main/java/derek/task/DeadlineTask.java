package derek.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The DeadlineTask class represents a task with a deadline. It extends the Task class
 * and includes a deadline date associated with the task.
 */
public class DeadlineTask extends Task {
    private String deadline;

    /**
     * Constructs a DeadlineTask object with the specified name and deadline.
     * @param name the name or description of the task
     * @param deadline the deadline date and time for the task in the format "dd/MM/yyyy HH:mm"
     */
    public DeadlineTask(String name, String deadline) throws DateTimeParseException {
        super(name);
        // Use LocalDateTime instead of LocalDate to handle date and time
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime inputDateTime = LocalDateTime.parse(deadline.trim(), inputFormatter);

        // Desired output format with time in 12-hour format and AM/PM
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
        this.deadline = inputDateTime.format(outputFormatter);

    }

    /**
     * Constructs a DeadlineTask object with the specified name, deadline, and completion status.
     * @param name the name or description of the task
     * @param deadline the deadline date and time for the task
     * @param isCompleted the completion status of the task (e.g., "X" for completed)
     */
    public DeadlineTask(String name, String deadline, String isCompleted) {
        super(name);
        this.deadline = deadline;
        if (isCompleted.equals("X")) {
            super.markCompleted();
        }
    }

    public String getDeadline() {
        return this.deadline;
    }

    public String getName() {
        return super.getName();
    }

    /**
     * Returns a string representation of the deadline task, including the task name
     * and deadline date formatted as "(by: date)".
     * @return a formatted string representation of the deadline task
     */
    @Override
    public String toString() {
        return String.format("[D]" + super.toString() + " (by: " + this.deadline + ")");
    }
}
