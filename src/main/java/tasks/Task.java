package tasks;

import java.util.ArrayList;
import java.util.Objects;

import chatterboxexceptions.ChatterboxExceptions;
import tags.Tag;


/**
 * Abstract class that defines Task
 */
public abstract class Task {
    private Boolean status;
    private String desc;
    private ArrayList<Tag> tags = new ArrayList<>();


    /**
     * initializes an undone task
     * @param desc is a string describing task
     * @throws ChatterboxExceptions.ChatterBoxNoInput if no input
     */
    public Task(String desc) throws ChatterboxExceptions.ChatterBoxNoInput {
        if (desc.trim().isEmpty()) {
            throw new ChatterboxExceptions.ChatterBoxNoInput("Error: No input for task");
        }
        this.status = false;
        this.desc = desc;
    }


    /**
     * initializes a task with status
     *
     * @return a string with tags
     * @throws ChatterboxExceptions.ChatterBoxNoInput if no input
     */
    public String getTags() {
        if (tags.size() <= 0) {
            return "";
        }
        return "|tags: " + tags.toString();
    }

    /**
     * returns a string with description of task
     * @return string of description
     */
    public String getDescription() {
        return this.desc + " " + this.getTags();
    }

    /**
     * gets status of task
     * @return true if done, false if undone
     */
    public Boolean getStatus() {
        return this.status;
    }

    /**
     * Sets status of task
     * @param stat true or false
     */
    public void setStatus(Boolean stat) {
        this.status = stat;
    }

    /**
     * gets the symbol of the task
     * @return a string representing the task
     */
    public String getTaskSymbol() {
        return "";
    }

    /**
     * gets the tags of the task
     * @return an arraylist of tags
     */
    public void addTag(Tag tag) {
        this.tags.add(tag);
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
}
