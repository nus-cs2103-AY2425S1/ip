public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }
    public void setProgress(boolean b) {
        this.isDone = b;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String printTask() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String markDone() {
        this.setProgress(true);
        return "Nice! I've marked this task as done:\n  " + this.printTask();
    }

    public String markIncomplete() {
        this.setProgress(false);
        return "OK! I've marked this task as not done yet:\n  " + this.printTask();
    }

}
