public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
        System.out.println("____________________________________________________________");
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println(this.toString());
        System.out.println("____________________________________________________________");

    }

    public void markAsNotDone() {
        isDone = false;
        System.out.println("____________________________________________________________");
        System.out.println("  OK, I've marked this task as not done yet:");
        System.out.println(this.toString());
        System.out.println("____________________________________________________________");
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}