package task;

import exception.TaskNameEmptyException;
import io.Saveable;

public class Task implements Saveable {
    private final String taskName;
    private boolean isDone;

    public Task(boolean isDone, String taskName) throws TaskNameEmptyException {
        if (taskName.isBlank()) {
            throw new TaskNameEmptyException();
        }
        this.taskName = taskName;
        this.isDone = isDone;
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

    @Override
    public String save() {
        return "| " + (isDone ? "1" : "0") + " | " + this.taskName;
    }
}
