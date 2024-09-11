package task;

import java.time.LocalDateTime;

public class Task {
    private String taskName;
    private boolean isCompleted;

    /**
     * Constructor for a new incomplete task.
     * 
     * @param taskName
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isCompleted = false;
    }

    public void setTaskName(String newName) {
        this.taskName = newName;
    }

    /**
     * Mark a task as completed.
     */
    public void markAsCompleted() {
        this.isCompleted = true;
        assert this.isCompleted : "Mark-as-complete command failed";
    }

    /**
     * Mark a task as incomplete.
     */
    public void markAsNotCompleted() {
        this.isCompleted = false;
        assert !this.isCompleted : "Mark-as-incomplete command failed";
    }

    @Override
    public String toString() {
        return this.isCompleted ? "[X] " + this.taskName : "[ ] " + this.taskName;
    }

    /**
     * Just returns the toString() version of the task.
     * Overriden in subclasses Event and Deadline to circumvent the
     * cleanup of the datetime object by Event::dateTimeToCleanString
     * and Deadline::dateTimeToCleanString (so these functions are not used)
     * 
     * @return a simple string representation of the task.
     */
    public String toEasyString() {
        return this.toString();
    }

    /**
     * Checks if the particular term is present in the task name.
     * 
     * @param term the term to check.
     * @return true if the term is present, false otherwise.
     */
    public boolean contains(String term) {
        return this.taskName.toLowerCase().contains(term.toLowerCase());
    }

    /**
     * Updates the task details accordingly. MUST BE OVERRIDEN.
     * 
     * @param newDesc the new description (if any)
     * @param fromDate the new from date (if any, only applicable to events)
     * @param toDate the new to date (if any, only applicable to deadlines and events)
     */
    public void update(String newDesc, LocalDateTime fromTime, LocalDateTime toTime) {
        return;
    }
}
