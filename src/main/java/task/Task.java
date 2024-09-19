package task;

import java.io.Serializable;
import java.util.regex.Pattern;

import utility.Tag;

/**
 * Provides a single task in a form of an object with properties such as task description and
 * a marker for whether a task is done. Serves as a parent class for all tasks related
 * child classes.
 */
public class Task implements Serializable {
    protected final boolean isDone;
    protected final String taskDescription;
    protected final Tag taskTag;


    /**
     * Default task constructor which will always initially be not done.
     * @param taskDescription a string to describe the task.
     * @param taskTag a tag for the task
     */
    public Task(String taskDescription, Tag taskTag) {
        this.isDone = false;
        this.taskDescription = taskDescription;
        this.taskTag = taskTag;
    }

    protected Task(boolean isDone, String taskDescription, Tag taskTag) {
        this.isDone = isDone;
        this.taskDescription = taskDescription;
        this.taskTag = taskTag;
    }

    /**
     * Using regex to find match on the task description.
     *
     * @return boolean if regex match found or not
     */
    public boolean findMatch(Pattern searchTerm) {
        return searchTerm.matcher(taskDescription).find();
    }

    public boolean ifTagMatch(String searchTerm) {
        return taskTag.isMatch(searchTerm);
    }

    /**
     * Marks task as done.
     *
     * @return the same task but with isDone set to true.
     */
    public Task markAsDone() {
        return isDone
            ? this
            : new Task(true, this.taskDescription, this.taskTag);
    }

    /**
     * Marks task as undone.
     *
     * @return the same task but with isDone set to false.
     */
    public Task markAsUndone() {
        return isDone
            ? new Task(this.taskDescription, this.taskTag)
            : this;
    }

    /**
     * Returns a string representation of the task. The consists of {@code "[  ]"} filled with
     * either a cross ({@code "X"}) or a whitespace to visually represent a checkbox,
     * followed by task description.
     *
     * @return a string representation of the task.
     */
    @Override
    public String toString() {
        return isDone
            ? "[X] " + taskDescription
            : "[ ] " + taskDescription;
    }
}
