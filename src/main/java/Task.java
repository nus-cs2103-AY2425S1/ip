public abstract class Task {
    private String desc;
    private boolean isDone;

    public Task(String desc, boolean isDone) {
        this.desc = desc;
        this.isDone = isDone;
    }

    public String getDesc() {
        return this.desc;
    }

    public boolean getStatus() {
        return this.isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public abstract String getType();

    public abstract String taskToString();

    @Override
    public String toString() {
        return this.desc;
    }
}
