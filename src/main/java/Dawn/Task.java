package Dawn;
public class Task {
    private String desc;
    private boolean isDone;

    public Task() {
        this.desc = "";
        this.isDone = false;
    }
    public Task(String description) {
        this.desc = description.trim();
        this.isDone = false;
    }

    protected String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    protected boolean isDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return this.desc;
    }

    protected void markAsDone() {
        this.isDone = true;
    }

    protected void markAsNotDone() {
        this.isDone = false;
    }

    protected String getDesc() {
        return this.desc;
    }

    protected boolean isAMatch(String d) {
        return this.desc.contains(d);
    }
}
