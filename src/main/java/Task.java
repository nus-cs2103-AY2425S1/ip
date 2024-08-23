public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) throws GrokInvalidUserInputException {
        if (description.isEmpty()) {
            throw new GrokInvalidUserInputException("Task description cannot be empty!");
        }
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

