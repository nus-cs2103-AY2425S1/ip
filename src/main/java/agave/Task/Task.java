package agave.Task;

import agave.logic.Deadline;
import agave.logic.Event;
import agave.logic.ToDo;

import java.util.ArrayList;

/**
 * Represents a task that can be added to the task list.
 */
public class Task {
    private String description;
    private ArrayList<Task> tasks;
    private boolean isDone;

    /**
     * Constructs a Task with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        assert description != null : "Description cannot be null";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status of the task as a string.
     *
     * @return A string representing whether the task is done or not.
     */
    public String getStatus() {
        return isDone ? "[X] " : "[ ] ";
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Unmarks the task as done.
     */
    public void unmarkAsDone() {
        isDone = false;
    }

    /**
     * Formats the task data for writing to a file.
     *
     * @return A string representing the task in a file-friendly format.
     */
    public String toWriteFormat() {
        assert (this instanceof ToDo) || (this instanceof Deadline) || (this instanceof Event) : "Invalid task type";
        return String.format("%s | %d | %s",
                this instanceof ToDo ? "T" : this instanceof Deadline ? "D" : "E",
                isDone ? 1 : 0,
                this.getCorrectFormat()
        );
    }

    /**
     * Parses a task from a formatted string.
     *
     * @param data The string containing the task data.
     * @return A Task object created from the string data.
     */
    public static Task parseTask(String data) {
        String[] split = data.split(" \\| ");
        Task task = split[0].equals("T")
                ? new ToDo(split[2])
                : split[0].equals("D")
                ? new Deadline(split[2], split[3]) // 2 is description, 3 is by
                : new Event(split[2], split[3], split[4]); // 2 is description, 3 is from, 4 is to
        if (split[1].equals("1")) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Returns the correct format of the task.
     *
     * @return A string representing the task in the correct format.
     */
    public String getCorrectFormat() {
        return description;
    }

    @Override
    public String toString() {
        return getStatus() + " " + getDescription();
    }
}