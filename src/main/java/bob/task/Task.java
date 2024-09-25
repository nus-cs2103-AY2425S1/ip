package bob.task;

import java.util.HashSet;
import java.util.Set;

/**
 * Abstract base class that all task types should inherit.
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

    @Override
    public String toString() {
        String tagsAsString = tags.stream().reduce("", (str, tag) -> str + " #" + tag);
        return String.format("[%s]%s %s", this.getStatusIcon(), tagsAsString, this.description);
    }
}
