package sentinel.task;

import java.time.LocalDateTime;

/**
 * Represents a task in the Sentinel application.
 * A Task has a description, a status indicating whether it's done or not, and methods to handle its representation.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task, indicating whether it is done or not.
     *
     * @return The status icon as "[X]" if done, "[ ]" otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Returns the string representation of the task, which is its description.
     *
     * @return The description of the task.
     */
    @Override
    public String toString() {
        return this.description;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a character representing the type of the task.
     *
     * @return 'T' for ToDo, 'D' for Deadline, 'E' for Event.
     */
    public char getTaskType() {
        if (this instanceof ToDo) {
            return 'T';
        } else if (this instanceof Deadline) {
            return 'D';
        } else {
            return 'E';
        }
    }

    /**
     * Returns a string representation of the task including its type, status icon, and description.
     *
     * @return A formatted string of the task.
     */
    public String listedString() {
        return this.classFirstChar() + this.getStatusIcon() + " " + this;
    }

    /**
     * Returns a string representing the class type of the task.
     *
     * @return A string representing the task type, e.g., "[T]", "[D]", "[E]".
     */
    public String classFirstChar() {
        return "[" + this.getTaskType() + "]";
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Marks the task as undone.
     */
    public void setUndone() {
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Converts a LocalDateTime to a formatted string.
     *
     * @param localDateTime The LocalDateTime to format.
     * @return A string representing the LocalDateTime in a formatted manner.
     */
    protected String localDateTimeToString(LocalDateTime localDateTime) {
        return localDateTime.getDayOfMonth() + " " + localDateTime.getMonth() + " " + localDateTime.getYear() + " " +
                padTimeLeft(localDateTime.getHour()) + padTimeRight(localDateTime.getMinute()) + " Hours (" +
                localDateTime.getDayOfWeek() + " " +
                (localDateTime.getHour() > 12 ? padTimeLeft(localDateTime.getHour() - 12) : localDateTime.getHour()) + ":" +
                padTimeRight(localDateTime.getMinute()) +
                (localDateTime.getHour() > 12 ? "pm)" : "am)");
    }

    /**
     * Pads the hour value to ensure it is two digits long.
     *
     * @param hour The hour value to pad.
     * @return A string representing the padded hour.
     */
    private String padTimeLeft(int hour) {
        return String.format("%2s", hour).replace(' ', '0');
    }

    /**
     * Pads the minute value to ensure it is two digits long.
     *
     * @param minute The minute value to pad.
     * @return A string representing the padded minute.
     */
    private String padTimeRight(int minute) {
        return String.format("%-2s", minute).replace(' ', '0');
    }
}
