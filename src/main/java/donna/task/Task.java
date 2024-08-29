package donna.task;

import donna.DonnaException;

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

    public boolean containsKeyword(String keyword) {
        return this.description.toLowerCase().contains(keyword);
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

    public abstract String getType(); //method to return whether task is a donna.task.ToDo, donna.task.Event or donna.task.Deadline
    public abstract String toFileFormat(); //method to convert tasks' data into the file's format

    @Override
    public String toString() {
        String isDoneStr = isDone ? "[X] " : "[ ] ";
        return "[" + getType() + "]" + isDoneStr + description + " ";
    }
}
