package tasks;

import javafx.util.converter.LocalDateTimeStringConverter;

import java.time.LocalDateTime;

public class ToDoTask extends Task {

    public ToDoTask(String description, boolean isDone) {
        super(description, isDone);
    }
    /**
     * A symbol denoting the task type.
     * @return a string denoting the task type.
     */
    @Override
    public String getTaskType() {
        return "T";
    }
    /**
     * Gets the description of the task.
     * @return a string containing all the relevant information of the Task.
     */
    @Override
    public String getDescription() {
        return this.getTaskType() + " | " + super.getDescription();
    }
    /**
     * Description of the task to be parsed.
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
    @Override
    public void updateTask(String field, String newValue) {
        this.setDescription(newValue + " ");
    }
}
