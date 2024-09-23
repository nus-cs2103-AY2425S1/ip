package chatbot.task;

import java.time.format.DateTimeFormatter;

import chatbot.exception.EmptyDescException;

/**
 * Task can be a ToDoTask, Deadline or an Event.
 * All tasks have a description and a marker isDone to indicate whether the task has been completed.
 */
public abstract class Task {
    protected static final DateTimeFormatter ORIGINALFORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    protected static final DateTimeFormatter PRINTFORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");

    private final String description;
    private boolean isDone;

    Task(String description) throws EmptyDescException {
        if (description == "") {
            throw new EmptyDescException();
        }
        this.description = description;
        this.isDone = false;
    }

    /** gets the description of the task */
    public String getDescription() {
        return description;
    }

    /** Marks the tasks as completed */
    public Task markAsDone() {
        this.isDone = true;
        return this;
    }

    /** Marks the tasks as not completed */
    public Task markAsNotDone() {
        this.isDone = false;
        return this;
    }

    private String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    /** Returns the original command that created this task */
    public String getOriginalCommand() {
        return this.description;
    }

    /** Returns true if there is a match of s in description, s.strip() to remove whitespaces */
    public boolean contains(String s) {
        return this.description.contains(s.strip());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Task othertask) {
            return this.description.equals(othertask.description);
        }
        return false;
    }

    @Override
    public String toString() {
        String s = "[" + this.getStatusIcon() + "] ";
        s += this.getDescription();
        return s;
    }
}
