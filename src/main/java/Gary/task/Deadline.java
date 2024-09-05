package Gary.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task, which has a description and a date by which the task is due.
 */
public class Deadline extends Task {
    protected String dueDate;
    private LocalDate deadline;

    // Formatters for outputting and parsing dates.
    static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
    static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Constructs a Deadline object with a description and a due date.
     *
     * @param description The description of the task.
     * @param dueDate The due date of the task in the format "yyyy-MM-dd".
     */
    public Deadline(String description, String dueDate) {
        super(description);
        this.deadline = parseDate(dueDate);
    }

    /**
     * Parses the given date string in the format "yyyy-MM-dd" and returns a LocalDate object.
     *
     * @param dateTime The date string to be parsed.
     * @return A LocalDate object representing the parsed date.
     */
    private LocalDate parseDate(String dateTime) {
        return LocalDate.parse(dateTime, inputFormatter);
    }

    /**
     * Returns a string representation of the Deadline task, including its type "[D]", completion status,
     * description, and due date.
     *
     * @return A string in the format "[D][X] description (by: MMM dd yyyy)" if done, or "[D][ ] description (by: MMM dd yyyy)" if not done.
     */
    @Override
    public String toString() {
        return "[D][" + (this.isDone ? "X" : " ") + "] " + this.description +
                " (by: " + this.deadline.format(outputFormatter) + ")";
    }

    /**
     * Converts the Deadline task into a string that can be written to a file.
     *
     * @return A string in the format "D | {isDone} | {description} | {dueDate}", where {isDone} is "1" if done and "0" if not.
     */
    @Override
    public String parseToFile() {
        return "D | " + (this.isDone ? "1" : "0") + " | " + this.description + " | " +
                this.deadline.format(inputFormatter);
    }

    /**
     * Checks if this Deadline object is equal to another object.
     *
     * @param o The object to be compared.
     * @return True if both objects are Deadline objects with the same description, completion status, and due date.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Deadline deadline = (Deadline) o;
        return super.equals(o) &&
                (dueDate == null ? deadline.dueDate == null : this.dueDate.equals(deadline.dueDate));
    }
}

