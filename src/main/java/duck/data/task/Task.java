package duck.data.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
        System.out.println("Nice! I've marked this task as done:\n" +
                this.toString());
        System.out.println();
    }

    public void markAsIncomplete() {
        isDone = false;
        System.out.println("OK, I've marked this task as not done yet:\n" +
                this.toString());
        System.out.println();
    }

    public String toFileFormat() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
