import java.util.ArrayList;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    public String addedConfirmation(ArrayList<Task> listOfTask) {
        return "Got it. I've added this task:\n" +
                "  " + this.toString() + "\n" +
                "Now you have " + listOfTask.size() + " tasks in the list.";
    }
}
