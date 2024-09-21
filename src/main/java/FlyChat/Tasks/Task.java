package flychat.tasks;

import java.util.HashSet;

/**
 * Represents the Tasks of the user.
 */
public abstract class Task {
    private boolean done;
    private String text;
    private HashSet<String> tags;

    /**
     * Constructs a new Task object.
     *
     * @param description
     */
    public Task(String description) {
        done = false;
        text = description;
        tags = new HashSet<>();
    }

    /**
     * Changes the done boolean to true to mark a task.
     */
    public void completeTask() {
        done = true;
    }

    /**
     * Changes the done boolean to false to unmark a task.
     */
    public void undoCompleteTask() {
        done = false;
    }

    /**
     * Adds a tag to the task.
     *
     * @param tag The tag to be added.
     */
    public void addTag(String tag) throws IllegalArgumentException {
        if (tag.equals("#")) {
            throw new IllegalArgumentException("Tags cannot be empty or start with spaces.");
        }

        tags.add(tag);
    }

    /**
     * Returns the tags of the task.
     */
    public HashSet<String> getTags() {
        return tags;
    }

    /**
     * Generates a string containing the info for the task object in the
     * appropriate format for saving into the save file..
     *
     * @return Formatted string to be saved to the save file.
     */
    public String formatStringForSaving() {
        return toString();
    }

    /**
     * Returns the description of the task.
     */
    public String getDescription() {
        return text;
    }

    @Override
    public String toString() {
        return "[" + (done ? "X" : " ") + "] " + text + " /tags [" + String.join(" ", tags) + "]";
    }
}
