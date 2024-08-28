public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void setTrue() {
        this.isDone = true;
    }

    public void setFalse() {
        this.isDone = false;
    }

    public String markTask() {
        this.setTrue();
        return this.toString();
    }

    public String unmarkTask() {
        this.setFalse();
        return this.toString();
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
