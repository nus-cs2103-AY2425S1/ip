package milo.tasks;
/**
 * Represents a task event containing
 * completion status, deletion status and description
 * also contains a static count of the task number
 */
public class Task {
    private final String description;
    private Boolean isCompleted = false;

    private Boolean isDeleted = false;

    /**
     * initialise a task object
     * task status set as not completed
     *
     * @param desc of the task
     */
    public Task(String desc) {
        this.description = desc;
    }

    /**
     * initialise a task object
     * task status may be set as completed
     *
     * @param desc of the task
     * @param isCompleted completion status
     */
    public Task(String desc, Boolean isCompleted) {
        this.description = desc;
        this.isCompleted = isCompleted;
    }

    /**
    * mark task completion status as completed
    */
    public void mark() {
        if (!this.isCompleted) {
            this.isCompleted = true;
        }
    }

    /**
    * mark task completion status as incomplete
    */
    public void unmark() {
        if (this.isCompleted) {
            this.isCompleted = false;
        }
    }

    /**
     * deletes current task
     * decrementing taskNumber by one
     */
    public void delete() {
        this.isDeleted = true;
    }

    public Boolean isSameTask(String taskDesc) {
        return this.description != null && this.description.contains(taskDesc);
    }

    /**
     * overrides the Object toString() method
     * provides completion and description information
     * format string for Ui
     */
    @Override
    public String toString() {
        return isCompleted ? "[X] " + description : "[ ] " + description;
    }

    /**
     * return different formatted string information of the task
     * provides completion and description information
     * format string for Storage
     */
    public String toTextString() {
        return isCompleted ? " | 1 | " + description : " | 0 | " + description;
    }
}
