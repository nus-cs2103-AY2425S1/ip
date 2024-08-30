package alex.task;

/**
 * Represents the task that the user wrote.
 * Contains a description and a completion status.
 */
public class Task {
    private String taskName;

    private boolean taskCompletionStatus;
    public Task(String taskName, boolean taskCompletionStatus) {
        this.taskName = taskName;
        this.taskCompletionStatus = taskCompletionStatus;
    }

    /**
     * Marks the Task as done.
     */
    public void markAsDone() {
        this.taskCompletionStatus = true;
    }

    /**
     * Marks the Task as not done yet.
     */
    public void markAsUndone() {
        this.taskCompletionStatus = false;
    }

    /**
     * Returns the String representation of the Task to be displayed to user
     * that includes its completion status and description.
     */
    @Override
    public String toString() {
        String box = "";
        if (this.taskCompletionStatus) {
            box = "[X]";
        } else {
            box = "[ ]";
        }

        return String.format("%s %s", box, this.taskName);
    }

    /**
     * Returns the String representation of the Task to be saved in the file
     * and retrieved when the chatbot is run again, which is similar to format of user input. The 2
     * different String representation formats allow reuse of Parser functions to convert the text in
     * the file back to Task objects when the file is loaded at the start of every run.
     */
    public String storageString() {
        return "";
    }
}
