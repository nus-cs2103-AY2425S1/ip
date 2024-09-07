package citadel.Task;

import java.util.ArrayList;

import citadel.ui.TextUI;

/**
 * Represents an abstract task in the Citadel application.
 * This class provides the common structure and behavior for all tasks.
 * Each task has a description and a status indicating whether it is done.
 */
public abstract class Task {

    /** The description of the task. */
    protected String description;

    /** The status of the task, indicating whether it is done. */
    protected boolean isDone;

    private ArrayList<String> tagList;

    /**
     * Constructs a new Task with the specified description.
     * The task is initially not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tagList = new ArrayList<String>();
    }

    /**
     * Returns the status icon of the task.
     * If the task is done, it returns "X", otherwise it returns " ".
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns a string representation of the task, including its status icon
     * and description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        assert !this.isDone : "Task is already marked as done";
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unMark() {
        assert this.isDone : "Task is already marked as not done";
        this.isDone = false;
    }

    /**
     * Adds a String Tag to the list of Tags.
     */
    public void addTag(String tag) {
        this.tagList.add(tag);
    }

    /**
     * Removes a String Tag from the list of Tags.
     *
     * @param index The index of the tag in the tagList.
     *
     * @return the removed String Tag
     */
    public String removeTag(int index) {
        return this.tagList.remove(index);
    }

    /**
     * Gets a String Tag from the list of Tags.
     *
     *
     * @return the called String Tag from getTag
     */
    public ArrayList<String> getTag() {
        return this.tagList;
    }

    /**
     * Gets a String Tag from the list of Tags.
     *
     * @return the called String Tag from getTag
     */
    public String printTags() {
        StringBuilder tasksToString = new StringBuilder();
        // Generate and accumulate all the Tag strings together into a string
        for (int i = 0; i < this.tagList.size(); i++) {
            String printString = String.format("%d. %s%n", i + 1, this.tagList.get(i));
            assert printString != null : "Tag string representation cannot be null";
            tasksToString.append(printString);
        }
        return TextUI.printMessage(tasksToString.toString());
    }

    /**
     * Returns a string representation of the task that can be used for
     * printing or saving to a file. This method must be implemented by
     * subclasses.
     *
     * @return A string representation of the task.
     */
    public abstract String printTask();
}
