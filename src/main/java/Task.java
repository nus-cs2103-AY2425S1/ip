public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) throws LightException{
        if (description.isEmpty()) {
            throw new LightException("Description cannot be empty");
        }
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }
    public void markAsUndone() {
        isDone = false;
    }
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}