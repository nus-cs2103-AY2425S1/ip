package bimo.tasks;

/**
 * Creates a Task with completion status and description.
 */
public class Task {
    private boolean isCompleted = false;
    private String priority = "";
    private String details;

    /**
     * Instantiates a Task object.
     *
     * @param details Description of object.
     */
    public Task(String details) {
        this.details = details;
    }

    /**
     * Sets task as completed.
     */
    public void markCompleted() {
        this.isCompleted = true;
    }

    /**
     * Sets task as uncompleted.
     */
    public void markUncompleted() {
        this.isCompleted = false;
    }

    /**
     * Converts task to string value with description.
     *
     * @return String value of task.
     */
    @Override
    public String toString() {
        String status = this.isCompleted ? "X" : " ";
        String taskString = String.format("[%s] %s",
                status, this.details);
        return taskString;
    }

    /**
     * Formats a task to text.
     *
     * @return Text format of status and description separated by |.
     */
    public String getTaskText() {
        String state = this.isCompleted ? "1" : "0";
        String taskAsText = state + "|" + this.details;
        return taskAsText;
    }

    /**
     * Returns details of tasks.
     *
     * @return Description of task.
     */
    public String getDetails() {
        return this.details;
    }

    /**
     * Sets the priority level of task.
     *
     * @param priority The level of priority indicated by user.
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * Formats priority of task to load text file.
     *
     * @return Representation of priority in text file.
     */
    public String convertPriorityToText() {
        String priorityLevel = this.priority.isEmpty()
                               ? ""
                               : this.priority + "~";
        return priorityLevel;
    }


    /**
     * Formats priority of task to use for subclass toString.
     *
     * @return String representation of priority.
     */
    public String convertPriorityToString() {
        String priorityLevel = this.priority.equals("")
                               ? ""
                               : "<" + this.priority + "> ";
        return priorityLevel;
    }
}
