package jeff.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor that creates a Task object
     *
     * @param description that describes what the task is
     */
    public Task (String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task (String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the string representation of whether the task is done or not
     *
     * @return the string representation of the task status
     */
    private String getStatusIcon() {
        return (this.isDone ? "[X]" : "[ ]");
    }

    /**
     * String representation of the task
     *
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    /**
     * Mark the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark the task as not done
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Get the task status
     *
     * @return true if the task is done and false if the task is not done
     */
    public boolean isDone() {
        return this.isDone;
    }

    public String toFileString() {
        if (this.isDone) {
            return "1 | " + this.description;
        } else {
            return "0 | " + this.description;
        }
    }

    public String getDateString(LocalDateTime date, String format) {
        return date.format(DateTimeFormatter.ofPattern(format));
    }

    public boolean isOnThisDate(LocalDate date) {
        return false;
    }

    /**
     * Checks if the task description contains the given name.
     *
     * @param name Given name to check against.
     * @return true if the task description contains the given name and false otherwise.
     */
    public boolean contains(String name) {
        return this.description.toLowerCase().contains(name.toLowerCase().trim());
    }
}
