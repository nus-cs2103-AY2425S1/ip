package panorama.task;

/**
 * Represents an abstract task with a name, status, and type.
 * Subclasses should extend this class to define specific types of tasks.
 */
public abstract class Task {
    private String name;
    private boolean isDone;
    private char taskType;

    /**
     * Constructs a {@code Task} with the specified name and type.
     * The task is initialized as not done.
     *
     * @param name The name or description of the task.
     * @param taskType The type of the task, represented as a character.
     */
    public Task(String name, char taskType) {
        this.name = name;
        this.isDone = false;
        this.taskType = taskType;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param isDone {@code true} if the task is completed, {@code false} otherwise.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }


    public String getName() {
        return this.name;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns a string representation of the task in a format suitable for file storage.
     * The format uses '|' as a separator between fields.
     *
     * @return A formatted string for writing the task to a file.
     */
    public String toFileString() {
        String output = "";
        output += taskType;
        output += "|";
        output += this.isDone ? "1" : "0";
        output += "|";
        output += this.name;
        return output;
    }

    /**
     * Returns a string representation of the task for displaying in the user interface.
     * The format includes the task type, completion status, and task name.
     *
     * @return A formatted string representing the task.
     */
    public String toString() {
        String output = "";
        output += "[" + taskType + "]";
        output += " ";
        output += this.isDone ? "[x]" : "[ ]";
        output += " ";
        output += this.name;
        return output;
    }
}
