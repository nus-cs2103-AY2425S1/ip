import java.util.ArrayList;

public abstract class Task {
    protected String description;
    protected boolean isDone = false;

    protected Task(String description) {
        this.description = description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // if task is done, mark with X
    }

    public void addTaskMessage(ArrayList<Task> tasks) {
        tasks.add(this);
        System.out.printf("\t%s%n", "------------------------------------------------------------------");
        System.out.printf("\t%s%n", "Got it. I've added this task:");
        System.out.printf("\t\t%s%n", this);
        System.out.printf("\t%s%n", "Now you have " + tasks.size() + " tasks in the list.");
        System.out.printf("\t%s%n", "------------------------------------------------------------------");
    }

    public abstract String getTaskType();

    public String toSaveFormat() {
        return String.format("%s | %d | %s", this.getTaskType(), (isDone ? 1 : 0), description);
    }

    @Override
    public String toString() {
        return "[" + getTaskType() + "]" + getStatusIcon() + " " + this.description;
    }

}
