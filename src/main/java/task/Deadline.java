package task;
import exception.InputFormatException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class represents a task with a specific deadline.
 * It extends the Task class and includes the deadline by which the task must be completed.
 */
public class Deadline extends Task{

    private final LocalDateTime byDate;

    /**
     * Constructs a Deadline with the specified description and deadline date.
     *
     * @param description The description of the task.
     * @param byDate      The date and time by which the task must be completed.
     */
    public Deadline(String description, LocalDateTime byDate) {
        super(description);
        this.byDate = byDate;
    }

    /**
     * Returns a string representation of the deadline task in a format suitable for file storage.
     * The format is: "D | doneStatus | description | byDate", where doneStatus is 1 if the task is done, 0 otherwise,
     * and byDate is formatted as "yyyy-MM-dd HH:mm".
     *
     * @return A string representation of the deadline task for file storage.
     */
    public String toFileFormatString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("D | %s | %s", super.toFileFormatString(), byDate.format(formatter));
    }

    /**
     * Returns a string representation of the deadline task, including its status, description, and deadline date.
     * The format is: "[D] [statusIcon] description (by: formattedDate)", where statusIcon is "X" if the task is done,
     * " " otherwise, and formattedDate is the deadline date formatted as "MMM dd yyyy h:ma".
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:ma");
        return String.format("[D] %s (by: %s)\n", super.toString(), byDate.format(formatter));
    }



    public static boolean matchDeadline(String s) {
        return s.startsWith("deadline");
    }
}
