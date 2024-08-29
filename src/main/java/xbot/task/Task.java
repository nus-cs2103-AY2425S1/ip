package xbot.task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;

    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public TaskType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done xbot.task with X
    }

    public void setIsDone() {
        isDone = true;
    }

    public void markAsDone() {
        isDone = true;
        System.out.println("Nice! I've marked this xbot.task as done:\n" +
                toString());
    }

    public void markAsUndone() {
        isDone = false;
        System.out.println("OK, I've marked this xbot.task as not done yet:\n" +
                toString());
    }

    public boolean isDone() {
        return isDone;
    }
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
