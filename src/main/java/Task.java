public class Task {
    private String taskDesc;
    private boolean isDone;

    Task(String taskDesc) {
        this.taskDesc = taskDesc;
        this.isDone = false;
    }

    public String markAsDone() throws ConverSageException {
        if (this.isDone) {
            throw new ConverSageException("This task is already marked as done...");
        }
        this.isDone = true;
        return "[X] " + this.taskDesc;
    }

    public String markAsUndone() throws ConverSageException {
        if (!this.isDone) {
            throw new ConverSageException("This task is already marked as not done...");
        }
        this.isDone = false;
        return "[ ] " + this.taskDesc;
    }

    public String getStatus() {
        return this.isDone ? "[X]" : "[ ]";
    }

    @Override
    public String toString() {
        return this.getStatus() + " " + this.taskDesc;
    }
}
