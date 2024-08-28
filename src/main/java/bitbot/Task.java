package bitbot;
/**
 * The class Task represents the different tasks that can be done.
 * It is the parent class of Todo, Deadline, Event classes.
 * Adapted from the partial solution in the question
 */
public class Task {
    protected String taskDescription;
    protected boolean isDone;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    public String finalString() {
        return isDone
                ? "[X] " + taskDescription
                : "[ ] " + taskDescription;
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

}