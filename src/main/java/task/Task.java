package task;

import exception.TaskNameEmptyException;

public class Task {
    private final String taskName;
    private boolean isDone;

    public Task(String taskName) throws TaskNameEmptyException {
        if (taskName.isBlank()) {
            throw new TaskNameEmptyException();
        }
        this.taskName = taskName;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + taskName;
    }
}
