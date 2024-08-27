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

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String toFileFormat() {
        return " ";
    }

}