package fred.Tasks;

import java.util.ArrayList;

/**
 * The abstract Task class represents a task with a description and a completion status.
 * It provides methods for marking the task as done or not done, and for generating
 * a string representation of the task for both display and data storage purposes.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected ArrayList<String> tags;

    /**
     * Constructs a Task with the given description. By default, the task is not done.
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
     *
     * @return "X" if the task is done, otherwise a space (" ").
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns a string representation of the task, including its status and description.
     *
     * @return A string in the format "[status] description".
     */
    @Override
    public String toString() {
        return String.format("[%s] %s %s", getStatusIcon(), description, getTags());
    }

    /**
     * Returns a string representation of the task in a format suitable for data storage.
     *
     * @return A string representing the task's data format, indicating its status and description.
     */
    public String getDataFormat() {
        return (isDone ? " | 1 | " + description : " | 0 | " + description);
    }

    public boolean checkForKeyword(String keyword) {
        String[] descriptionParts = description.split(" ");
        for (String descriptionPart : descriptionParts) {
            if (descriptionPart.equals(keyword)) {
                return true;
            }
        }
        return false;
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public String getTags() {
        if (tags.isEmpty()) {
            return "";
        }
        StringBuilder tagsAsString = new StringBuilder();
        tagsAsString.append("(");
        for (String tag : tags) {
            tagsAsString.append(tag);
            tagsAsString.append(", ");
        }
        if (!tagsAsString.isEmpty()) {
            tagsAsString.setLength(tagsAsString.length() - 2);
        }
        tagsAsString.append(")");
        return tagsAsString.toString();
    }
}
