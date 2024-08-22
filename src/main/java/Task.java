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
        System.out.println("\tNice! I've marked this task as done:\n\t  " + this);
    }

    public void unmarkAsDone() {
        isDone = false;
        System.out.println("\tOK, I've marked this task as not done yet:\n\t  " + this);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}
