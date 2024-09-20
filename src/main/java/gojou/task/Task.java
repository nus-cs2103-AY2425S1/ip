package gojou.task;

/**
 * Represents a task with a description and a completion status.
 */
public class Task implements Comparable<Task> {
    private String taskName;
    private boolean isCompleted;
    private Priority priority;

    /**
     * Constructs a Task with the specified description and completion status.
     *
     * @param taskName The name or description of the task.
     * @param isCompleted A boolean indicating whether the task is completed.
     */
    public Task(String taskName, boolean isCompleted, Priority priority) {
        this.taskName = taskName;
        this.isCompleted = isCompleted;
        this.priority = priority;
    }

    /**
     * Marks the Task as done.
     */
    public void markAsDone() {
        this.isCompleted = true;
    }

    /**
     * Marks the Task as not done yet.
     */
    public void markAsUndone() {
        this.isCompleted = false;
    }

    /**
     * Returns the String representation of the Task to be displayed to the user,
     * including its completion status and description.
     *
     * @return A String representation of the Task.
     */
    @Override
    public String toString() {
        String completionBox = this.isCompleted ? "[X]" : "[ ]";
        String priorityBox;
        switch (this.priority) {
        case HIGH:
            priorityBox = "[HIGH]";
            break;
        case MEDIUM:
            priorityBox = "[MEDIUM]";
            break;
        case LOW:
            priorityBox = "[LOW]";
            break;
        default:
            priorityBox = "[-]";
        }

        return String.format("%s%s %s", priorityBox, completionBox, this.taskName);
    }

    /**
     * Returns the String representation of the Task to be saved in a file.
     * This format is similar to the format of user input to facilitate
     * conversion of text from the file back to Task objects when the file
     * is loaded at the start of each run.
     *
     * @return A String representation of the Task for storage.
     */
    public String toStorageString() {
        String completionBox = this.isCompleted ? "[X]" : "[ ]";
        String priorityString;
        switch (this.priority) {
        case HIGH:
            priorityString = "//high";
            break;
        case MEDIUM:
            priorityString = "//medium";
            break;
        case LOW:
            priorityString = "//low";
            break;
        default:
            priorityString = "";
        }
        return String.format("%s %s %s", completionBox, this.taskName, priorityString);
    }

    /**
     * Checks if the task description contains the specified search string.
     *
     * @param searchString The string to search for within the task description.
     * @return True if the task description contains the search string, false otherwise.
     */
    public boolean checkHaveSearchString(String searchString) {
        return this.taskName.contains(searchString);
    }

    @Override
    public int compareTo(Task task) {
        return this.priority.compareTo(task.priority);
    }
}

