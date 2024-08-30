package BrainRot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class represents a task with a deadline.
 * It extends the Task class and includes a deadline by which the task must be completed.
 * The deadline is stored as a formatted string.
 */
public class Deadline extends Task {
    // The end time of the task, formatted as a string.
    protected String end;

    /**
     * Constructs a new Deadline task with a specified description and deadline.
     *
     * @param command The description of the task.
     * @param end The deadline for the task in the format "MMM dd yyyy HH:mm".
     *            Example: "Dec 02 2019 18:00".
     */
    public Deadline(String command, String end) {
        super(command);  // Pass task description to BrainRot.Task class

        // Define the correct formatter that matches "Dec 02 2019 18:00"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

        // Parse the input string using the correct formatter
        LocalDateTime endDate = LocalDateTime.parse(end.trim(), formatter);

        // Store the formatted date as a string in the desired format
        this.end = endDate.format(formatter);
    }

    /**
     * Constructs a new Deadline task with a specified description and deadline,
     * used when the deadline string is already in the correct format (e.g., when loading from a file).
     *
     * @param command The description of the task.
     * @param end The deadline for the task as a formatted string.
     * @param fromFile A boolean indicating whether this constructor is used when loading from a file.
     */
    public Deadline(String command, String end, boolean fromFile) {
        super(command);
        this.end = end;
    }

    /**
     * Converts the Deadline task into a formatted string suitable for saving to a file.
     *
     * @return A string representing the Deadline task in the format "[D][X or  ]/description/end".
     */
    @Override
    public String toFileString() {
        return "[D][" + (isDone ? "X" : " ") + "]/" + description + "/" + end;
    }

    /**
     * Converts the Deadline task into a formatted string for display.
     *
     * @return A string representing the Deadline task in the format "[D][X or  ] description (by: end)".
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + end + ")";  // Ensure the end time is displayed
    }
}
