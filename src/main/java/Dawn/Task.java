package Dawn;
public class Task {
    protected String desc;
    protected boolean isDone;

    public Task() {
        this.desc = "";
        this.isDone = false;
    }
    public Task(String description) {
        this.desc = description.trim();
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public boolean isDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return this.desc;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getDesc() {
        return this.desc;
    }
}
