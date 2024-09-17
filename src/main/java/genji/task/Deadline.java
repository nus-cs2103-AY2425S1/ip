package genji.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A class that deals with deadline tasks
 */
public class Deadline extends Task {
    private LocalDateTime time;

    /**
     * Constructor of deadline class
     * @param name Task's name
     * @param time The deadline
     */
    public Deadline(String name, LocalDateTime time) {
        super(name);
        this.time = time;
    }

    /**
     * Gets the deadline
     * @return LocalDateTime of the deadline
     */
    public LocalDateTime getDate() {
        return this.time;
    }

    /**
     * Formats the deadline into strings used for saving to file
     * @return The String to be saved
     */
    @Override
    public String toListString() {
        return "D" + super.toListString() + " | " + time.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

    /**
     * Formats the deadline into strings used for display
     * @return The String to be displayed
     */
    @Override
    public String toString() {
        return "[D]" +super.toString() + " (by: " + time.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) +")";
    }
}
