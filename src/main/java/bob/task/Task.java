package bob.task;

import java.util.HashSet;
import java.util.Set;

import bob.exception.LineCorruptedException;

/**
 * Abstract base class that all task types should inherit.
 * All tasks must define:
 * <ul>
 * <li>A public and static {@code ENCODED_LETTER} char field that
 * {@code Storage} uses to check if an encoded string corresponds to that particular task type.</li>
 *
 * <li>A public and static {@code decode(String) throws bob.exception.LineCorruptedException} method that
 * decodes corresponding encoded lines in the data file.</li>
 * </ul>
 * Classes that inherit this class and is in this package is collected by {@code Storage} to be used.
 *
 * @see bob.Storage
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected Set<String> tags;

    /**
     * Constructs a task with the given description and tags.
     *
     * @param description description of the task
     * @param tags an array of strings that the task is tagged with
     */
    public Task(String description, String... tags) {
        this.description = description;
        this.isDone = false;
        this.tags = new HashSet<>(Set.of(tags));
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String[] getTags() {
        return tags.toArray(new String[0]);
    }

    /**
     * Returns the status icon of this task.
     *
     * @return "X" if this task is marked as done, " " otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks this task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Tags this task with the given tag name. A task can be tagged with multiple names.
     *
     * @param tagName name of the tag to tag this task with
     * @return true if this task is not already tagged with the given name
     */
    public boolean tag(String tagName) {
        return tags.add(tagName);
    }

    /**
     * Removes the tag with the given tag name from this task.
     *
     * @param tagName the name of the tag to remove
     * @return true if this task was previously tagged with the given tag name
     */
    public boolean unTag(String tagName) {
        return tags.remove(tagName);
    }

    /**
     * Removes all tags from this task.
     */
    public void clearTags() {
        tags.clear();
    }

    /**
     * Encodes this task to be stored in the data file.
     *
     * @return the encoded string representation of this task
     */
    public abstract String encode();

    /**
     * Decodes the encoded string representation of this task in the data file.
     * All task types must implement this method.
     *
     * @param encodedString the encoded string representation of this task
     * @return the decoded task
     * @throws LineCorruptedException if the encoded string does not follow the encoding format of this task
     */
    public static Task decode(String encodedString) throws LineCorruptedException {
        return null;
    }

    @Override
    public String toString() {
        String tagsAsString = tags.stream().reduce("", (str, tag) -> str + " #" + tag);
        return String.format("[%s]%s %s", this.getStatusIcon(), tagsAsString, this.description);
    }
}
