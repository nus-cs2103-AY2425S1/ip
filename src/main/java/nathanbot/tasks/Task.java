package nathanbot.tasks;

import java.io.Serializable;

/**
 * Represents a task with a description and completion status. 
 * Parent class of Deadline, Event, and Todo.
 * Javadocs using Copilot
 */
public class Task implements Serializable {
    private final String description;
    private boolean isDone;
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a Task with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmarkAsDone() {
        isDone = false;
    }

    /**
     * Returns a string representation of the task, including its completion status and description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }
}