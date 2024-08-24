import java.util.ArrayList;

public class Task {
    protected String description;
    protected boolean isDone = false;

    public Task(String description) {
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


    @Override
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }

}
