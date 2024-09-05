package maga.task;

/**
 * Represents a Todo task, which is a basic task without a specific date or time.
 * It extends the {@code Task} abstract class.
 */
public class TodoTask extends Task {

    /**
     * Constructs a {@code TodoTask} with the specified completion status and description.
     *
     * @param isDone      A boolean indicating whether the task is completed.
     * @param description The description of the task.
     */
    public TodoTask(boolean isDone, String description) {
        super(description);
        this.isDone = isDone;
    }

    /**
     * Returns the task type, which is always "[T]" for a todo task.
     *
     * @return A string representing the task type.
     */
    public String getTaskType() {
        return "[T]";
    }

    /**
     * Returns a string representation of the {@code TodoTask} object, formatted for saving to a file.
     * The format is "T | isDone | description", where {@code isDone} is represented by 1 for true
     * and 0 for false.
     *
     * @return A string representing the {@code TodoTask} object.
     */
    @Override
    public String toString() {
        int isDoneNum = 0;
        if (isDone) {
            isDoneNum = 1;
        }
        return "T | " + isDoneNum + " | " + description;
    }

    /**
     * Returns a formatted string for printing the task, which includes the task type, status icon,
     * and description.
     *
     * @return A string representing the {@code TodoTask} formatted for display.
     */
    @Override
    public String printTask() {
        return this.getTaskType() + this.getStatusIcon() + this.getDescription();
    }
}