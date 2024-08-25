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

    /**
     * Checks if the task contains the specified keyword in its description.
     *
     * @param keyword Keyword to be checked within the task description.
     * @return true if the task contains the specified keyword in its description; false otherwise.
     */
    public boolean hasKeyword(String keyword) {
        if (this.description.contains(keyword)) {
            return true;
        } else {
            return false;
        }
    }

    public String stringUI() {
        return "[" + this.taskEnum.name() + "][" + this.getStatusIcon() + "] " + this.description;
    }

    public String stringStorage() {
        return this.taskEnum.name() + " | " + this.getStatusIcon() + " | " + this.description;
    }

}
