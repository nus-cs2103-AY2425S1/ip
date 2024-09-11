package rizz.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task, which is a type of Task that has a due date.
 * A Deadline task contains a description and a date/time by which the task must be completed.
 */
public class Deadline extends Task {
    private final LocalDateTime by;
    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
    DateTimeFormatter readFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Constructs a new Deadline task.
     *
     * @param text The description of the Deadline task.
     * @param by The date and time by which the task must be completed.
     * @param isDone The completion status of the task. If true, the task is marked as done.
     */
    public Deadline(String text, LocalDateTime by, boolean isDone) {
        super(text,isDone);
        this.by = by;
    }

    /**
     * Exports the Deadline task as a formatted string for saving.
     * The format will be: "D | <isDone> | <text> | <by>"
     * where <by> is the deadline formatted as "yyyy-MM-dd HHmm".
     *
     * @return The string representation of the Deadline task for file storage.
     */
    @Override
    public String export() {
        return "D | " + (isDone ? "1" : "0") + " | " + text + " | " + by.format(readFormatter);
    }

    /**
     * Returns a string representation of the Deadline task.
     * The format will be: "[D][<status>] <text> (by: <by>)"
     * where <status> is either "X" for done or " " for not done, and <by> is the deadline formatted as "MMM dd yyyy".
     *
     * @return The string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +  by.toLocalDate().format(outputFormatter) + ")";
    }
}