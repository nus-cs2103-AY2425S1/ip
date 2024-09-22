package task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a task
 */
public class Task {
    private String description;
    private boolean isDone;
    private List<String> tags = new ArrayList<>();
    /**
     * Constructor the task
     * @param description The description for the task
     */
    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Adds a tag to the task
     * @param tag The tag to be added
     */
    public void addTag(String tag) {
        if (!tags.contains(tag)) {
            tags.add(tag);
        }
    }

    /**
     * Removes a tag from the task
     * @param tag The tag to be removed
     */
    public void removeTag(String tag) {
        if (tags.contains(tag)) {
            tags.remove(tag);
        }
    }
    /**
     * Checks if the task is tagged with a specific tag
     * @param tag The tag to be checked
     * @return true if the task is tagged
     */
    public boolean isTagged(String tag) {
        return tags.contains(tag);
    }

    /**
     * Converts tags to string
     * @return The string reprsents all tags
     */
    public String getTagsToString() {
        String str = "tags:";
        boolean isFirst = true;
        for (String tag : tags) {
            if (!isFirst) {
                str += " ";
            }
            str += tag;
            isFirst = false;
        }
        return str;
    }

    @Override
    public String toString() {
        String pref = isDone ? "[X] " : "[ ] ";
        return pref + description;
    }
}
