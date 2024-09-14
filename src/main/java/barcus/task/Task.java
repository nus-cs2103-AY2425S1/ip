package barcus.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Task to be completed
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected List<String> tags;

    /**
     * Constructor for Task without isdone status
     * @param description description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tags = new ArrayList<>();
    }

    /**
     * Constructor with isDone
     * @param description of task
     * @param isDone status of task
     */
    public Task(String description, boolean isDone, String tags) {
        this.description = description;
        this.isDone = isDone;
        tags = tags.replace("[", "");
        tags = tags.replace("]", "");
        this.tags = List.of(tags.split(","));
    }

    /**
     * Returns how the status should be represented
     * @return
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks task as done
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Unmarks task as undone
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Converts task into save file friendly string
     * @return save file friendly string
     */
    public String convertToSavedString() {
        String status = this.isDone ? "1" : "0";
        return status + " | " + this.description + " | " + getTagsString();
    }

    /**
     * Returns whether the description contains the substring
     * @param toFind string to find
     * @return true if description contains the substring
     */
    public boolean containsSubstring(String toFind) {
        return this.description.toLowerCase().contains(toFind.toLowerCase());
    }

    /**
     * Adds tag to list of tags
     * @param tag String tag
     */
    public void addTag(String tag) {
        if (!tag.startsWith("#")) {
            tag = "#" + tag;
        }
        tag = tag.toLowerCase();
        if (!tags.contains(tag)) {
            tags.add(tag);
        }
    }

    /**
     * Removes tag from list of tags
     * @param tag String tag
     */
    public void removeTag(String tag) {
        if (!tag.startsWith("#")) {
            tag = "#" + tag;
        }
        tag = tag.toLowerCase();
        tags.remove(tag);
    }

    private String getTagsString() {
        return "[" + String.join(", ", tags) + "]";
    }

    /**
     * Return string representation
     * @return string representation
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description + " " + getTagsString();
    }
}
