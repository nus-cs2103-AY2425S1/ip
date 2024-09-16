package max.task;

import java.util.ArrayList;

/**
 * The Task class represents a task with a description and a status indicating whether it is done.
 * This class serves as the base class for more specific types of tasks, such as ToDo, Deadline, and Event.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    private ArrayList<String> tags;


    /**
     * Constructs a Task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tags = new ArrayList<>();
    }

    /**
     * Returns the status icon of the task.
     * An "X" indicates that the task is done, while a space indicates that it is not done.
     *
     * @return A string representing the task's status icon.
     */
    public String getStatusIcon() {

        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     * The string contains the task description and associated tags, if any.
     * Tags are displayed as hashtags.
     *
     * @return A string representing the task, including the description and any tags.
     */
    @Override
    public String toString() {
        StringBuilder taskString = new StringBuilder();
        taskString.append("[").append(this.getStatusIcon()).append("] ");
        taskString.append(this.getDescription());

        // Append tags as hashtags if there are any
        if (!tags.isEmpty()) {
            taskString.append(" ");
            for (String tag : tags) {
                taskString.append("#").append(tag).append(" ");
            }
        }

        return taskString.toString().trim(); // Trim to remove the trailing space
    }

    /**
     * Converts the task to a string format suitable for saving to a file.
     *
     * @return A string in the format "X | status | description", where the status is either 1 (done) or 0 (not done).
     */
    public String toFileFormat() {
        return String.format("X | %d | %s", isDone ? 1 : 0, description);
    }

    /**
     * Adds a tag to the task if it is not already present.
     *
     * @param tag The tag to be added to the task.
     */
    public void addTag(String tag) {
        if (!tags.contains(tag)) {
            tags.add(tag);
        }
    }

    /**
     * Removes a tag from the task.
     *
     * @param tag The tag to be removed from the task.
     */
    public void removeTag(String tag) {
        tags.remove(tag);
    }

    /**
     * Retrieves the list of tags associated with the task.
     *
     * @return A list of tags for the task.
     */
    public ArrayList<String> getTags() {
        return tags;
    }

    /**
     * Retrieves the tags associated with the task as a comma-separated string.
     *
     * @return A string of tags separated by commas, or an empty string if there are no tags.
     */
    public String getTagsString() {
        if (tags.isEmpty()) {
            return "";
        }
        return String.join(",", tags);
    }
}
