package panorama.task;

import java.time.LocalDate;

import panorama.DateParser;

/**
 * Represents a deadline task with a name and a due date.
 * A deadline task has a specific date and time by which it should be completed.
 */
public class Deadline extends Task {
    private LocalDate date;

    /**
     * Constructs a {@code Deadline} task with the specified name and due date.
     *
     * @param name The name or description of the deadline task.
     * @param date The due date and time for the task.
     */
    public Deadline(String name, LocalDate date) {
        super(name, 'D');
        this.date = date;
    }

    /**
     * Returns a string representation of the deadline task in a format suitable for file storage.
     * The format includes the task details and the due date, separated by '|'.
     *
     * @return A formatted string for writing the deadline task to a file.
     */
    @Override
    public String toFileString() {
        return super.toFileString() + "|" + DateParser.formatForHardDisk(date);
    }

    /**
     * Returns a string representation of the deadline task for displaying in the user interface.
     * The format includes the task details and the due date.
     *
     * @return A formatted string representing the deadline task.
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + DateParser.format(date) + ")";
    }
}
