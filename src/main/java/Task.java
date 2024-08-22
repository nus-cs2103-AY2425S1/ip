public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor to initialise a task.
     * @param description Input based on user.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns whether or not a task is completed.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " " );  //mark task completed with an X
    }

    /**
     * Returns the stored description of this particular task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as completed or not completed.
     * @param value Whether the task is completed.
     * @param currDescription the input by the user
     * @return String representation based on whether the task is marked as completed or not completed.
     */
    public String markTask(Boolean value, String currDescription) {
        if (value) {
            this.isDone = true;
            String finishedMarking = "OK, I've marked this task as done:\n\t"
                    + "[X] "
                    + currDescription;
            return finishedMarking;
        } else {
            this.isDone = false;
            String finishedUnmarking = "OK, I've marked this task as not done yet:\n\t"
                    + "[ ] "
                    + currDescription;
            return finishedUnmarking;
        }
    }
}
