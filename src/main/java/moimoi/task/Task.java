package moimoi.task;

import java.time.LocalDate;

public class Task {

    protected TaskEnum taskEnum;
    protected String description;
    protected boolean isDone;

    public Task(TaskEnum taskEnum, String description) {
        this.taskEnum = taskEnum;
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public boolean occurringOn(LocalDate date) {
        return false;
    }

    public String stringUI() {
        return "[" + this.taskEnum.name() + "][" + this.getStatusIcon() + "] " + this.description;
    }

    public String stringStorage(){
        return this.taskEnum.name() + " | " + this.getStatusIcon() + " | " + this.description;
    }

}
