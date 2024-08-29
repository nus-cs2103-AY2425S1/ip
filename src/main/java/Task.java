public class Task {
    protected String description;
    protected boolean isDone;
    protected String marker = " ";

    /**
     * Constructor to initialise a task.
     * @param description Input based on user.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor to initialise a task previously recorded.
     * @param description Input based on user.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns whether or not a task is completed.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns a string representation for a line item in the printed list,
     */
    public String getTaskListItem() {
        return("[" + taskLetter() +"][" + this.getStatusIcon() + "] " + this.description);
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
                    + "["
                    + taskLetter()
                    + "]"
                    + "[X] "
                    + this.description;
            Bob.printLines(finishedMarking);
        } else {
            this.isDone = false;
            String finishedUnmarking = "OK, I've marked this task as not done yet:\n\t"
                    + "["
                    + taskLetter()
                    + "]"
                    + "[ ] "
                    + this.description;
            Bob.printLines(finishedUnmarking);
        }
    }

    public String taskLetter() {
        return " ";
    }

    /**
     * Returns a string representation of the file format in which we store the task.
     */
    public String fileFormat () {
        String done = isDone ? "1" : "0";
        return this.taskLetter() + " | " + done + " | " + this.description;
    }
}
