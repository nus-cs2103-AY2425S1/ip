public class Task {
    protected String desc;
    protected boolean isDone;

    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }


    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    private String getDesc() {
        return desc;
    }

    public String fullToString() {
        return "[" + getStatusIcon() + "] " + desc;
    }

    @Override
    public String toString() {
        return desc;
    }
}
