public class Task {
    protected String description;
    protected  boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void changeStatus(boolean newIsDone) {
       this.isDone = newIsDone;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
