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

    public String getFormattedTask() {
        return String.format("[%s] %s", getStatusIcon(), getDescription());
    }

    public void markAsDone() {
        isDone = true;
        System.out.println("\t" + "Nice! I've marked this task as done:");
        System.out.println("\t\t" + getFormattedTask());
    }

    public void markAsUndone() {
        isDone = false;
        System.out.println("\t" + "OK, I've marked this task as not done yet:");
        System.out.println("\t\t" + getFormattedTask());
    }
}