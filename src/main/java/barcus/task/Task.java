package barcus.task;

import java.util.ArrayList;
import java.util.Arrays;
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
        if (tags.equals("")) {
            this.tags = new ArrayList<>();
        } else {
            this.tags = new ArrayList<>(Arrays.asList(tags.split(", ")));
        }
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
     * Checks if taks is tagged by specific tag
     * @param tag String to check
     * @return true if contains the tag
     */
    public boolean containsTag(String tag) {
        String temp = tag;
        if (!tag.startsWith("#")) {
            temp = "#" + temp;
        }
        temp = temp.toLowerCase();
        return tags.contains(temp);
    }

    /**
     * Adds tag to list of tags
     * @param tag String tag
     */
    public void addTag(String tag) {
        String temp = tag;
        if (!tag.startsWith("#")) {
            temp = "#" + temp;
        }
        temp = temp.toLowerCase();
        if (!tags.contains(temp)) {
            tags.add(temp);
        }
    }

    /**
     * Removes tag from list of tags
     * @param tag String tag
     */
    public void removeTag(String tag) {
        String temp = tag;
        if (!tag.startsWith("#")) {
            temp = "#" + temp;
        }
        temp = temp.toLowerCase();
        tags.remove(temp);
    }

    /**
     * Converts tags into string to display and save
     * @return String of tags
     */
    private String getTagsString() {
        return "[" + String.join(", ", tags) + "]";
    }

    /**
     * Returns string representation
     * @return string representation
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description + " " + getTagsString();
    }
}
