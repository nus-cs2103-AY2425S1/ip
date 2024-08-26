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

    public String getDescription() {
        return this.description;
    }

    private String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    private String getStatusNum() {
        return (isDone ? "1" : "0");
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

    public void delete() {
        System.out.println("    Noted. I've removed this task:");
    }

    public String toFileFormat() {
        return this.getStatusNum() + " .. " + this.getDescription();
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.getDescription();
    }
}
