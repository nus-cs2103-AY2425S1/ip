package winner;

/**
 * Represents a Task which includes the description of the task, the completion status and total number of tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new Task with the given description. The task is initially not done.
     * The total task count is incremented by 1 when a new task is created.
     *
     * @param description Description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks a Task as done and returns a String confirming the Task has been marked as done.
     *
     * @return A String indicating that the Task has been marked as done.
     */
    public String markDone() {
        this.isDone = true;
        return """
                Yay! One task down!
                I have marked the following task as DONE :)""";
    }

    /**
     * Marks a Task as undone and returns a String confirming the task has been marked as undone.
     *
     * @return A String indicating the Task has been marked as undone.
     */
    public String unmarkDone() {
        this.isDone = false;
        return """
                Oh no! I shall mark the following task as UNDONE :(
                You can do it!""";
    }

    /**
     * Returns a String representation of a Task, including its completion status and description.
     *
     * @return A String representing the Task, including its completion status and description.
     */
    public String taskToString() {
        if (isDone) {
            return "[X] " + description;
        }
        return "[ ] " + description;
    }

    /**
     * Deletes a Task and returns a String confirming the task has been deleted.
     *
     * @return A String indicating that the task has been deleted.
     */
    public String deleteTask() {
        return "Alright! I have removed this task for you.";
    }
}
