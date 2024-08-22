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
     * Returns a string representation for a line item in the printed list,
     */
    public String getTaskListItem() {
        return("[" + this.getStatusIcon() + "] " + this.description);
    }

    /**
     * Marks the task as completed or not completed.
     * @param value Whether the task is completed.
     * @return String representation based on whether the task is marked as completed or not completed.
     */
    public void markTask(Boolean value) {
        if (value) {
            this.isDone = true;
            String finishedMarking = "OK, I've marked this task as done:\n\t"
                    + "[X] "
                    + this.description;
            Bob.printLines(finishedMarking);
        } else {
            this.isDone = false;
            String finishedUnmarking = "OK, I've marked this task as not done yet:\n\t"
                    + "[ ] "
                    + this.description;
            Bob.printLines(finishedUnmarking);
        }
    }
}
