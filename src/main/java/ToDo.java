public class ToDo extends Task {
    private boolean isDone;

    public ToDo(String taskName) {
        super(taskName);
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + super.toString();
    }
}
