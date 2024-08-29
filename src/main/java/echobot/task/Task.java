package echobot.task;

import echobot.exception.TaskNameEmptyException;
import echobot.io.Saveable;

public class Task implements Saveable {
    private final String taskName;
    private boolean isDone;

    public Task() {
        this.taskName = "";
    }

    public Task(boolean isDone, String taskName) throws TaskNameEmptyException {
        if (taskName.isBlank()) {
            throw new TaskNameEmptyException();
        }
        this.taskName = taskName;
        this.isDone = isDone;
    }

    /**
     * Mark this task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Mark this task as undone.
     */
    public void markUndone() {
        this.isDone = false;
    }

    public boolean isDone() {
        return this.isDone;
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
