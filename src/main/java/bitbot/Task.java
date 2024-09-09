package bitbot;

/**
 * This is the abstract Task class which creates the many other subclasses
 * such as ToDos, Events, Deadlines.
 */
public abstract class Task {
    protected String taskDescription;
    protected boolean isDone;
    protected boolean isTagged;
    protected String tag;

    /**
     * This is a Task constructor that takes in a description.
     *
     * @param taskDescription the description of the task
     */
    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
        this.isTagged = false;
        this.tag = "";
    }

    /**
     * This returns the string as to which the user sees it in the console.
     *
     * @return a String
     */
    public String finalString() {
        String statusOfTag = isTagged ? "[#" + tag + "]" : "";
        return statusOfTag + (isDone
                ? "[X] " + taskDescription
                : "[ ] " + taskDescription);
    }

    /**
     * Marks a task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks a task as undone
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * This prints out the format in which the task will be stored in the file.
     * This method will be overridden in the subclasses.
     * @return a String which is stored in the file.
     */
    public String toFileFormat() {
        return " ";
    }

    /**
     * Tags a task according to the user's input
     *
     * @param tag the user's input of the tagging message
     */
    public void markAsTagged(String tag) {
        this.isTagged = true;
        this.tag = tag;
    }

    /**
     * Untags a task
     */
    public void markAsNotTagged() {
        this.isTagged = false;
        this.tag = "";
    }

}
