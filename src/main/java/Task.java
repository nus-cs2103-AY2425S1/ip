public class Task {

    private String description;
    private boolean isDone;
    private int taskID;
    private static int lastID = 0;

    public Task (String description) {
        this.description = description;
        this.isDone = false;
        this.taskID = lastID++;
    }

    private String getDescription() {
        return this.description;
    }

    private String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    public int getTaskID() {
        return this.taskID;
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("    Nice! I've marked this task as done:");
    }

    public void markAsNotDone() {
        this.isDone = false;
        System.out.println("    OK, I've marked this task as not done yet:");
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.getDescription();
    }
}
