package bestie.task; // import libraries necessary for bestie to understand the dates

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Creates an instance of a deadline task.
 */
public class Deadline extends Task {

    protected String by;
    protected LocalDateTime deadline;
    private String formattedDeadline;

    /**
     * Creates a new deadline task.
     *
     * @param description Task description.
     * @param by Deadline of the task, given in the form of YYYY-MM-DD HHMM.
     */
    public Deadline(String description, String by, Priority priority) {
        super(description, priority);
        this.by = by;
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.deadline = LocalDateTime.parse(by, inputFormat);
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM d yyyy h:mm a");
        this.formattedDeadline = this.deadline.format(outputFormat);
    }

    /**
     * Formats the task in a consistent format to be saved in the bestie.txt file.
     * Allows for easier retrieval and converting tasks stored in bestie.txt into the TaskList.
     *
     * @return Formatted tasks to be saved in bestie.txt.
     */
    @Override
    public String toSaveFormat() {
        String storeCompleted = "";

        if (this.isDone) {
            storeCompleted = "1";
        } else {
            storeCompleted = "0";
        }
        // Store format: " D | 0 | read book | by Sunday | HIGH
        return "D | " + storeCompleted + " | " + this.description + " | " + this.by + " | " + this.priority;
    }

    /**
     * Formats the task to be displayed on the console.
     *
     * @return String representation of the deadline task on the console.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.formattedDeadline + "), priority: " + this.priority.toString();
    }

}
