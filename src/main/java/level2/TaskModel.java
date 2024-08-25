package level2;

public class TaskModel {
    private final String title;
    private boolean status;

    public TaskModel(String title) {
        this.title = title;
        this.status = false;
    }

    public String getTitle() {
        return this.title;
    }

    public void markAsDone() {
        this.status = true;
        System.out.println("Nice! I've marked this task as done: \n" + this.toString());
    }

    public void markAsUndone() {
        this.status = false;
        System.out.println("OK, I've marked this task as not done yet: \n" + this.toString());
    }

    public String getStatusIndicator() {
        return (status ? "[X] " : "[ ] "); // mark done task with X
    }

    @Override
    public String toString() {
        return this.getStatusIndicator() + this.title;
    }
}