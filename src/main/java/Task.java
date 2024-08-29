public abstract class Task {
    protected final String name;
    protected boolean isCompleted;

    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    protected void setCompleted() {
        this.isCompleted = true;
    }

    protected void setUncompleted() {
        this.isCompleted = false;
    }

    protected String getStatusIcon() {
        return this.isCompleted ? "X" : " ";
    }

    @Override
    public String toString() {
        return "["
                + this.getStatusIcon()
                + "] "
                + this.name;
    }

    public abstract String parseTaskInfo();
}
