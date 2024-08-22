public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;

    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String mark() {
        this.isDone = true;
        return "Nice! I've marked this task as done:\n  " + this;
    }

    public String unmark() {
        this.isDone = false;
        return "OK, I've marked this task as not done yet:\n  " + this;
    }

    public String delete() {
        return "Noted. I've removed this task:\n  " + this;
    }

    public String toString() {
        return String.format("[%s][%s] %s", this.type.name().charAt(0), this.getStatusIcon(), this.description);
    }
}
