public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) throws BobException {
        if (description == "") {
            throw new BobException("OOPS!!! The description of a todo cannot be empty.");
        }
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public boolean markAsDone() {
        this.isDone = true;
        return true;
    }

    public boolean markAsNotDone() {
        this.isDone = false;
        return true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
