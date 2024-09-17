package tasks;

import javafx.util.converter.LocalDateTimeStringConverter;

import java.time.LocalDateTime;

/**
 * Represents a To-Do Task where it has its own description and completion status.
 */
public class ToDoTask extends Task {

    public ToDoTask(String description, boolean isDone) {
        super(description, isDone);
    }
    /**
     * Returns the symbol denoting the task type.
     * @return a string denoting the task type.
     */
    @Override
    public String getTaskType() {
        return "T";
    }
    /**
     * Returns the description of the task.
     * @return a string containing all the relevant information of the Task.
     */
    @Override
    public String getDescription() {
        return this.getTaskType() + " | " + super.getDescription();
    }
    /**
     * Returns the description of the task to be parsed.
     * @return a string to be parsed.
     */
    @Override
    public String toString() {
        return String.format("%s | %s | %s",
                this.getTaskType(), getStatusIcon(), super.description);
    }
    @Override
    protected LocalDateTime parseTime(String time) {
        return LocalDateTime.now();
    }
    /**
     * Replaces the value of the field with the new value.
     *
     * @param field Field that is being replaced with the new value.
     * @param newValue Value replacing the old value.
     * @throws IllegalArgumentException If there are invalid fields or incorrect input
     * of command.
     */
    @Override
    public void updateTask(String field, String newValue) {
        this.setDescription(newValue + " ");
    }
}
