package Nah.Data;

import java.time.LocalDateTime;

public abstract class Task {
    private String description;
    private boolean isDone = false;
    Task(String description) {
        this.description = description;
    }


    /**
     * Mark the task as done by setting the {@code isDone} flag to {@code true}.
     */

    public void mark() {
        isDone = true;
    }

    /**
     * Unmark the task by setting the {@code isDone} flag to {@code false}.
     */

    public void unMark() {
        isDone = false;
    }
    public int getStatus() {
        return isDone ? 1 : 0;
    }
    public String getTask() {
        return this.description;
    }

    /**
     * Return a brief description of task
     * @return
     */
    public abstract String brief();
    private String getStatusMark() {
        return isDone ? "[X]" : "[ ]";
    }
    public abstract LocalDateTime endTime();


    @Override
    public String toString() {
        return getStatusMark() + " " + this.description;
    }

}
