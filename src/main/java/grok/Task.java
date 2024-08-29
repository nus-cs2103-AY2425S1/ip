package grok;

public abstract class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    public Task(String description) throws GrokInvalidUserInputException {
        if (description.isEmpty()) {
            throw new GrokInvalidUserInputException("Grok.Task description cannot be empty!");
        }
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, Boolean isDone) throws GrokInvalidUserInputException {
        if (description.isEmpty()) {
            throw new GrokInvalidUserInputException("Grok.Task description cannot be empty!");
        }
        this.description = description;
        this.isDone = isDone;
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
    public String serialize() {
        return String.join(" | ", isDone ? "X" : " ", description);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

