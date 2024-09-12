package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    private final String NEW_LINE = "\n";
    protected final String OPEN = "[";
    protected final String CLOSE = "]";
    protected final String BAR = " | ";

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

        String UNMARKED = "OK, I've unmarked this task as not done yet:" + NEW_LINE;
        return UNMARKED + OPEN + this.getStatusIcon() + CLOSE + this.description;
    }

    public String markTask() {
        this.isDone = true;

        String MARKED = "Nice! I've marked this task as done:" + NEW_LINE;
        return MARKED + OPEN + this.getStatusIcon() + CLOSE + this.description;
    }

    @Override
    public String toString() {
        return OPEN + getStatusIcon() + CLOSE + description;
    }
}
