package seanbot.Tasks;

/**
 * A Task has a description and a status indicating whether it is done.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * 
     * @return "X" if the task is done, otherwise a blank space.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Marks the task as done.
     */
    public void Done() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void Undone() {
        this.isDone = false;
    }

    /**
     * Converts the Task to a string suitable for saving to a file.
     *
     * @return A string representation of the Task for file storage.
     */
    public String toFileString() {
        return (this instanceof Todo ? "T" :
                this instanceof Deadline ? "D" :
                this instanceof Event ? "E" : " ") + " | " +
                (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns a string representation of the Task, including its status.
     *
     * @return A string representation of the Task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
