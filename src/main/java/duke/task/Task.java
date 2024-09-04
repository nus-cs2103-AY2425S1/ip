package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public abstract String toFileString();

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String unMarkTask() {
        this.isDone = false;
        return "OK, I've unmarked this task as not done yet: \n"
                + "   [" + this.getStatusIcon() + "] " + this.description;
    }
    public String markTask() {
        this.isDone = true;
        return "Nice! I've marked this task as done: \n"
                + "   [" + this.getStatusIcon() + "] " + this.description;
    }
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
