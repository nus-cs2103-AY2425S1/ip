package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import update.Updatable;

public abstract class Task implements Updatable {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "1" : "0");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unMark() {
        this.isDone = false;
    }
    /**
     * Gets the description of the task.
     * @return a string containing all the relevant information of the Task.
     */

    public String getDescription() {
        return this.getStatusIcon() + " | " + this.description;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    /**
     * Parses the string into the LocalDateTime format.
     * @param time a string to be parsed.
     * @return the LocalDateTime in the ideal format.
     * @throws DateTimeParseException if there is an error parsing the String.
     */
    protected abstract LocalDateTime parseTime(String time) throws DateTimeParseException;

    @Override
    public abstract void updateTask(String field, String newValue) throws IllegalArgumentException;

    /**
     * A symbol denoting the task type.
     * @return a string denoting the task type.
     */

    public String getTaskType() {
        return "";
    }

}
