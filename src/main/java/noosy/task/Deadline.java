package noosy.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task, which is a specific type of task with a due date.
 */
public class Deadline extends Task {

    /**
     * The due date of the deadline task.
     */
    private LocalDate due;

    /**
     * The formatter used for parsing input date strings.
     */
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * The formatter used for formatting output date strings.
     */
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Constructs a new Deadline with the given name and due date.
     * The status is set to false (not completed) by default.
     *
     * @param name The name or description of the deadline task.
     * @param due  The due date of the task.
     */
    public Deadline(String name, LocalDate due) {
        super(name);
        this.due = due;
    }

    /**
     * Constructs a new Deadline with the given name, status, and due date.
     *
     * @param name   The name or description of the deadline task.
     * @param status The completion status of the task.
     * @param due    The due date of the task.
     */
    public Deadline(String name, boolean status, LocalDate due) {
        super(name, status);
        this.due = due;
    }

    /**
     * Gets the due date of the deadline task.
     *
     * @return The due date of the task.
     */
    public LocalDate getDate() {
        return this.due;
    }

    /**
     * Returns a string representation of the deadline task for file storage.
     * The format is "T | status | name | due_date", where status is inherited from the Task class.
     *
     * @return A formatted string containing the task's type, status, name, and due date.
     */
    @Override
    public String storeInFileAsString() {
        String formattedDate = (due != null) ? due.format(OUTPUT_FORMATTER) : "Date can't be processed by noosy.Noosy";
        return String.format("T | %s | %s | %s", super.storeInFileAsString(), this.name, this.due);
    }

    /**
     * Returns a string representation of the deadline task, including its type, status, name, and due date.
     *
     * @return A string representation of the deadline task, prefixed with "[D]".
     */
    @Override
    public String toString() {
        String formattedDate = (due != null) ? due.format(OUTPUT_FORMATTER) : "Date can't be processed by noosy.Noosy";
        String desc = String.format("[D]%s (by: %s)", super.toString(), formattedDate);
        return desc;
    }
}
