package tasks;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import chatterboxexceptions.ChatterboxExceptions;
import tags.Tag;


/**
 * Abstract class that defines Task.
 * A Task has a description, status and a set of tags.
 */
public abstract class Task {
    private Boolean status;
    private String desc;
    private Set<Tag> tags = new HashSet<>();


    /**
     * Initializes an undone task.
     *
     * @param desc String describing task.
     * @throws ChatterboxExceptions.ChatterBoxNoInput if no input string.
     */
    public Task(String desc) throws ChatterboxExceptions.ChatterBoxNoInput {
        if (desc.trim().isEmpty()) {
            throw new ChatterboxExceptions.ChatterBoxNoInput("Error: No input for task");
        }
        this.status = false;
        this.desc = desc;
    }


    /**
     * Gets the string representation of all tags under a tasks.
     *
     * @return String representation of all tags.
     *
     */
    public String getTags() {
        if (tags.size() <= 0) {
            return "";
        }
        StringBuilder tagString = new StringBuilder();
        for (Tag tag : tags) {
            tagString.append(tag.toString()).append(" ");
        }

        return "/tags: " + tagString.toString();
    }

    /**
     * Returns a string with description of task.
     *
     * @return string of description of task.
     */
    public String getDescription() {
        return this.desc + " ";
    }

    /**
     * Gets status of task.
     *
     * @return true if done, false if undone.
     */
    public Boolean getStatus() {
        return this.status;
    }

    /**
     * Sets status of task.
     *
     * @param stat true or false.
     */
    public void setStatus(Boolean stat) {
        this.status = stat;
    }

    /**
     * Gets the symbol of the associated task class.
     *
     * @return a string representing the task.
     */
    public String getTaskSymbol() {
        return "";
    }

    /**
     * Adds a tag to the task.
     *
     * @param tag the tag to add to task list of tag.
     */
    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    /**
     * Removes the tag from the task.
     *
     * @param tag the tag to be removed.
     */
    public void removeTag(Tag tag) {
        this.tags.remove(tag);
    }
    @Override
    public String toString() {
        return this.getDescription();
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Task other = (Task) obj;
        return Objects.hash(this.getDescription()) == Objects.hash(other.getDescription());
    }

    /**
     * Returns a string with description of task without tags.
     *
     * @return string of description of task without tags.
     */
    public abstract String descNoTags();
}
