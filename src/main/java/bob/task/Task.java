package bob.task;

import java.util.HashSet;
import java.util.Set;

import bob.exception.LineCorruptedException;
import bob.exception.WrongTaskException;

/**
 * Abstract base class that all task types should inherit.
 * All must define a <br>
 * <code>public static decode() throws WrongTaskException, LineCorruptedException</code> <br>
 * method that decodes corresponding encoded lines in the data file.
 * Classes that inherit this class and is in this package is collected by the {@code Storage} to be used.
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
     * @throws WrongTaskException if the encoded string does not represent this task
     * @throws LineCorruptedException if the encoded string does not follow the encoding format of this task
     */
    public static Task decode(String encodedString) throws WrongTaskException, LineCorruptedException {
        return null;
    }

    @Override
    public String toString() {
        String tagsAsString = tags.stream().reduce("", (str, tag) -> str + " #" + tag).trim();
        return String.format("[%s] %s %s", this.getStatusIcon(), tagsAsString, this.description);
    }
}
