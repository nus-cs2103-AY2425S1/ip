package com.meow.com.tasks;
public class Task {
    private String taskName;
    private boolean isDone;
    
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }
    public void setDone(Boolean bool) {
        this.isDone = bool;
    }
    public String getTaskName() {
        return this.taskName;
    }
    public boolean isDone() {
        return this.isDone;
    }
    public String getCompletionChar() {
        return isDone ? "X" : " ";
    }

    public String toString() {
        return "[" + getCompletionChar() + "] " + taskName;
    }

    public String getExtra() {
        return "";
    }

    public String getType() {
        return "";
    }
    

}
