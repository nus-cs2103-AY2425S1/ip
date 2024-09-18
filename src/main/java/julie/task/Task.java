package julie.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * An abstract class that encapsulates the Tasks stored by the Chat Bot.
 */
public abstract class Task {
    /** The string description of the Task. */
    public final String taskString;
    /** The completion status of the Task */
    private boolean isCompleted;
    /** The List of Tags associated with this Task */
    private List<String> tagList;

    /**
     * Public constructor for a Task.
     *
     * @param s The description for the task.
     */
    public Task(String s) {
        this.taskString = s;
        this.tagList = new ArrayList<>();
        isCompleted = false;
    }

    /**
     * Marks the task as completed if it was initially not completed.
     */
    public void markCompleted() {
        this.isCompleted = true;
    }

    /**
     * Marks the task as incomplete if it was initially complete.
     */
    public void unmarkCompleted() {
        this.isCompleted = false;
    }
    @Override
    public String toString() {
        assert !Objects.equals(taskString, "") : "Task String should not be empty.";
        String output = "";
        if (isCompleted) {
            output = "[x]";
        } else {
            output = "[ ]";
        }
        return String.format("%s %s %s", output, taskString, this.getTagsString());
    }

    /**
     * Returns the representation of the task for storage purposes.
     *
     * @return The string representation of the task in storage format.
     */
    public abstract String toStorageString();

    /**
     * Adds the given Tag for the String.
     * @param tag the Tag to be added.
     */
    public void tag(String tag) {
        this.tagList.add(tag);
    }

    /**
     * Retyrns the Tags associated with this task;
     * @return The String representation of the tags.
     */
    private String getTagsString() {
        if (tagList.isEmpty()) {
            return "";
        } else {
            return tagList.toString();
        }
    }
}
