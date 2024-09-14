package alfred.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an abstract task with a description and completion status.
 * This class provides methods for marking tasks as done or undone,
 * and for handling different task types such as "ToDos", "Deadlines", and "Events".
 */
public abstract class Task {
    public static final String NO_TAG = "No Tags";

    protected String description;
    protected boolean isDone;
    private List<String> tags;

    /**
     * Constructs a new <code>Task</code> with the specified description.
     * The task is initially marked as not done.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tags = new ArrayList<>();
    }

    /**
     * Constructs a new <code>Task</code> with the specified description, done status and tags.
     *
     * @param description Description of the task.
     * @param isDone The completion status of the task.
     * @param tags The list of tags associated with the task
     */
    public Task(String description, boolean isDone, List<String> tags) {
        this.description = description;
        this.isDone = isDone;
        this.tags = new ArrayList<>(tags);
    }

    /**
     * Returns the task's status as a string icon.
     *
     * @return "X" if the task is done, otherwise a space character.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Checks if the task description contains the specified keyword.
     *
     * @param keyword The keyword to search for in the task description.
     * @return True if the description contains the keyword, false otherwise.
     */
    public boolean containsKeyword(String keyword) {
        return description.contains(keyword);
    }

    /**
     * Marks the task as completed by setting its status to done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not completed by resetting its status.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Adds a tag to the task.
     *
     * @param tag The tag to assign to the task.
     */
    public void addTag(String tag) {
        tags.add(tag);
    }

    /**
     * Removes the tag from the task.
     * If the tag is not present, the list remains unchanged.
     *
     * @param tag The tag to remove from the task.
     */
    public void untag(String tag) {
        tags.remove(tag);
    }

    /**
     * Returns the tags formatted as a string.
     * If the tag list is empty, returns "No Tags".
     *
     * @return A string representing the tags, separated by spaces. If no tags exist, returns "No Tags".
     */
    public String getTagsAsString() {
        if (tags.isEmpty()) {
            return NO_TAG;
        }
        return String.join(" ", tags);
    }

    /**
     * Returns the file format representation of the task.
     * The format includes task type, status, and description, and may include additional details.
     *
     * @return A string representing the task in file format.
     */
    public abstract String toFileFormat();

    /**
     * Returns a string representation of the task, including its status and description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", getStatusIcon(), getTagsAsString(), description);
    }
}
