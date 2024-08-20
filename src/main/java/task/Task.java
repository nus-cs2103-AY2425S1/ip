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

    public Task(boolean isDone, String taskName) throws TaskNameEmptyException {
        this(taskName);
        this.isDone = isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public String getTxtSavedToFile() {
        return "| " + (isDone ? "1" : "0") + " | " + this.taskName;
    }

    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + taskName;
    }
}
