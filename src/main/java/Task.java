public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) throws TaskArgumentMissingException {
        if (description == null || description.isEmpty()) {
            throw new TaskArgumentMissingException(getEmptyDescriptionErrorMessage());
        }
        this.description = description;
        isDone = false;
    }

    public abstract String getEmptyDescriptionErrorMessage();

    public boolean isDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    @Override
    public String toString() {
        // formats task as "[statusIcon] description"
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
