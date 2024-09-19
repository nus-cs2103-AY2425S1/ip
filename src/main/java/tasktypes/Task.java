package tasktypes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The {@code Task} class represents a task with a description and a completion status.
 */
public class Task {
    
    /** The description of the task. */
    protected String description;
    
    /** The completion status of the task. */
    protected boolean isDone;
    
    /**
     * Constructs a new {@code Task} with the specified description.
     * The task is initially marked as not done.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    
    /**
     * Constructs a new {@code Task} with the specified description and completion status.
     *
     * @param description the description of the task
     * @param isDone the completion status of the task
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }
    
    /**
     * Returns the status icon representing whether the task is done or not.
     *
     * @return "[X]" if the task is done, "[ ]" if the task is not done
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }
    
    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }
    
    /**
     * Marks the task as not done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }
    
    /**
     * Returns the description of the task.
     *
     * @return the description of the task
     */
    public String getDescription() {
        return this.description;
    }
    
    /**
     * Returns a formatted string representing the task in storage format.
     *
     * @return a string in the format "| doneStatus | description"
     */
    public String storageFormat() {
        String doneStatus = String.valueOf(this.isDone ? 1 : 0);
        return "| " + doneStatus + " | " + this.getDescription();
    }
    
    /**
     * Returns a formatted date string for printing purposes.
     *
     * @param dateConversion the {@code LocalDate} to format
     * @return a formatted date string in the "MMM d yyyy" pattern
     */
    public String dateFormatPrintVersion(LocalDate dateConversion) {
        return dateConversion.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
    
    /**
     * Returns a string representation of the task, including its status and description.
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.getDescription();
    }
    
    public LocalDate getStartDate() {
        return LocalDate.MAX;
    }
}
