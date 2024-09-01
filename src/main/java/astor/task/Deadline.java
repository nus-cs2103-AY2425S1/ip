package astor.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 *
 * Deadline tasks includes task information and deadline for the task. Has
 */
public class Deadline extends Task {
    private final LocalDateTime deadline;

    /**
     * Constructs a Deadline object with a task description and a deadline string.
     *
     * @param taskInfo a description of the task
     * @param deadline a string representing the deadline in ISO-8601 format
     */
    public Deadline(String taskInfo, String deadline) {
        super(taskInfo);
        this.deadline = LocalDateTime.parse(generateParse(deadline));
    }

    /**
     * Constructs a Deadline object with a task description and a LocalDateTime deadline.
     *
     * @param taskInfo a description of the task
     * @param deadline a {@code LocalDateTime} object representing the deadline
     */
    public Deadline(String taskInfo, LocalDateTime deadline) {
        super(taskInfo);
        this.deadline = deadline;
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * The string representation includes the task type, completion status, task description,
     * and the deadline formatted as "MMM dd yyyy".
     *
     * @return a string representation of the deadline task
     */
    @Override
    public String toString() {
        String s = "[D] ";
        if (this.isDone()) {
            s += "[X] ";
        } else {
            s += "[ ] ";
        }
        s += this.getTaskInfo() + " (by: "
                + this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        return s;
    }

    /**
     * Returns a string description of the task data for storing the task data.
     *
     * The string includes the task type, completion status, task description, and the deadline.
     *
     * @return a string description of the task data
     */
    @Override
    public String dataDescription() {
        int i = isDone() ? 1 : 0;
        return "D | " + i + " | " + this.getTaskInfo() + " | " + deadline;
    }
}
