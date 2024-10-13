package echobot.task;

import java.util.Arrays;

public class TaskStub extends Task {
    private final String taskName;
    private boolean isDone;

    public TaskStub(boolean isDone, String taskName) {
        super();
        this.taskName = taskName;
        this.isDone = isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public boolean isKeywordContained(String keyword) {
        return Arrays.asList(taskName.split(" ")).contains(keyword);
    }

    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + taskName;
    }

    @Override
    public String save() {
        return "| " + (isDone ? "1" : "0") + " | " + this.taskName;
    }

    public boolean equals(TaskStub task) {
        return this.taskName.equals(task.taskName) && this.isDone == task.isDone;
    }
}
