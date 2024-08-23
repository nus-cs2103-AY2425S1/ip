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

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
        System.out.println("\t" + "Nice! I've marked this task as done:");
        System.out.println("\t\t" + this);
    }

    public void markAsUndone() {
        isDone = false;
        System.out.println("\t" + "OK, I've marked this task as not done yet:");
        System.out.println("\t\t" + this);
    }

    public void deleteTask() {
        System.out.println("\t" + "Noted. I've removed this task:");
        System.out.println("\t\t" + this);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getDescription());
    }
}