package Gary.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The {@code Deadline} class represents a task with a description and a specific due date.
 * It is a subclass of the {@code Task} class.
 */
public class Deadline extends Task {
    private LocalDate deadline;  // The due date of the deadline task

    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Constructs a {@code Deadline} object with the specified description and due date.
     *
     * @param description The description of the deadline task.
     * @param dueDate The due date of the task in "yyyy-MM-dd" format.
     */
    public Deadline(String description, String dueDate) {
        super(description);
        this.deadline = parseDate(dueDate);
    }

    /**
     * Parses a date string into a {@code LocalDate} object using the input formatter.
     *
     * @param dateTime The date string to be parsed.
     * @return The parsed {@code LocalDate} object.
     */
    private LocalDate parseDate(String dateTime) {
        return LocalDate.parse(dateTime, INPUT_FORMATTER);
    }

    /**
     * Returns a string representation of the {@code Deadline} task.
     * The format includes the type of task, its status, and the due date.
     *
     * @return A string representation of the {@code Deadline} task.
     */
    @Override
    public String toString() {
        return "[D][" + (this.isDone ? "X" : " ") + "] " + this.description +
                " (by: " + this.deadline.format(OUTPUT_FORMATTER) + ")";
    }

    /**
     * Converts the {@code Deadline} task into a string format suitable for saving to a file.
     *
     * @return A string representation of the {@code Deadline} task for file storage.
     */
    @Override
    public String parseToFile() {
        return "D | " + (this.isDone ? "1" : "0") + " | " + this.description + " | " +
                this.deadline.format(INPUT_FORMATTER);
    }

    /**
     * Checks if this {@code Deadline} is equal to another object.
     * Two deadlines are considered equal if they have the same description and due date.
     *
     * @param obj The object to compare with this {@code Deadline}.
     * @return {@code true} if the specified object is equal to this {@code Deadline}, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Deadline otherDeadline = (Deadline) obj;
        return super.equals(otherDeadline) && this.deadline.equals(otherDeadline.deadline);
    }
}
