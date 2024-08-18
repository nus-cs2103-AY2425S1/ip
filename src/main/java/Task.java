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
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public String getDescription(int i) {
        return (i + 1) + ".[" + getStatusIcon() + "] " + description;
    }

    public void unMarkTask() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet: \n" +
                "   [" + this.getStatusIcon() + "] " + this.description);
    }
    public void markTask() {
        this.isDone = true;
        System.out.println("Nice! I've unmarked this task as done: \n" +
                "   [" + this.getStatusIcon() + "] " + this.description);
    }
    @Override
    public String toString() {
        return "added: " + description;
    }
}
