public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone  = false;
    }

    public String getStatusIcon() {
        // tasks that are completed are marked with X
        // tasks that are not completed are just empty
        return (this.isDone ? "X" : " ");
    }

    // mark the task as done
    public void markTaskDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }
}
