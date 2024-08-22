public abstract class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) throws DonnaException {
        if (description.trim().isEmpty()) {
            throw DonnaException.emptyDescription(this.getClass().getSimpleName()); //this error is universal to all types of tasks
        }

        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markNotDone() {
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public abstract String getType(); //method to return whether task is a ToDo, Event or Deadline

    @Override
    public String toString() {
        String isDoneStr = isDone ? "[X] " : "[ ] ";
        return "[" + getType() + "]" + isDoneStr + description + " ";
    }
}
