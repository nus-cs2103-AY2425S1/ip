package matcha.task;

import java.time.format.DateTimeFormatter;

import matcha.exception.MatchaException;

/**
 * Represents a task with a description and status.
 */
public class Task {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy h:mm a");
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task given a description.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        assert description != null : "Task description cannot be null!";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the format for inputted date and time.
     *
     * @return Format for inputted date and time.
     */
    public static DateTimeFormatter getInputFormat() {
        return INPUT_FORMAT;
    }

    /**
     * Returns the format for outputted date and time.
     *
     * @return Format for outputted date and time.
     */
    public static DateTimeFormatter getOutputFormat() {
        return OUTPUT_FORMAT;
    }

    /**
     * Marks the task as done.
     *
     * @throws MatchaException If the task is already marked as done.
     */
    public void markDone() throws MatchaException {
        if (this.isDone) {
            throw new MatchaException("Task is already marked as done!");
        }
        this.isDone = true;
        assert this.isDone : "Task should be marked as done!";
    }

    /**
     * Marks the task as not done.
     *
     * @throws MatchaException If the task is already marked as not done.
     */
    public void markNotDone() throws MatchaException {
        if (!this.isDone) {
            throw new MatchaException("Task is already marked as not done!");
        }
        this.isDone = false;
        assert !this.isDone : "Task should be marked as not done!";
    }

    /**
     * Returns the status icon of the task.
     *
     * @return Status icon of the task.
     */
    public String getStatusIcon() {
        return (this.isDone ? "[X] " : "[ ] ");
    }

    /**
     * Returns the string representation of the task with its description
     * and status when saving to a file.
     *
     * @return String representation of the task when saving to a file.
     */
    public String toSaveString() {
        return (this.isDone ? "1" : "0") + " | " + this.description;
    }

    /**
     * Checks if keyword is present in the task description.
     *
     * @param keyword The keyword to search for.
     * @return Returns true if keyword is found. Otherwise, returns false.
     */
    public boolean containsKeyword(String keyword) {
        return this.description.contains(keyword);
    }

    /**
     * Checks if the task is a duplicate of another task.
     *
     * @param task The task to compare with.
     * @return Returns true if the task is a duplicate. Otherwise, returns false.
     */
    public boolean checkDuplicate(Task task) {
        int taskIconLength = 8;
        if (!(task instanceof Task)) {
            return false;
        }

        if (task == this) {
            return true;
        }
        //get the substring of the task description without the status icon
        String taskSubstring = this.toString().substring(taskIconLength);
        String otherTaskSubstring = task.toString().substring(taskIconLength);
        return taskSubstring.equals(otherTaskSubstring);
    }

    /**
     * Returns the string representation of the task with its description
     * and status when printing to the user.
     *
     * @return String representation of the task when printing to the user.
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + this.description;
    }
}

