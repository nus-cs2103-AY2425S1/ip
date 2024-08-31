package chatbot.util;

import chatbot.util.Task;

public class Task {
    protected String description;
    protected boolean isDone;
    private TaskType taskType = null;

    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    public void changeStatus() {
        if (this.isDone) {
            this.isDone = false;
        } else {
            this.isDone = true;
        }
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public boolean getTaskStatus() {
        return isDone;
    }

    public TaskType getTaskType() {
        return this.taskType;
    }

    public String getTaskInfo() {
        return null;
    }


    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
