package neuro.task;

import java.util.ArrayList;

/**
 * The {@code Task} class represents a task with a description and a completion status.
 */
public abstract class Task {
    private final String description;
    private boolean isDone;
    private ArrayList<String> tags;

    /**
     * Constructs a Task object with the specified description
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        assert !description.isEmpty() : "Description should not be empty!";
        this.description = description;
        this.isDone = false;
        this.tags = new ArrayList<>();
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
    public void markUndone() {
        this.isDone = false;
    }

    public void addTag(String newTag) {
        this.tags.add(newTag);
    }

    /**
     * Returns a String representation of the task,
     * including its completion status and description.
     *
     * @return a String representation of the task
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }

    /**
     * Returns a String formatted for saving the task's data to a file.
     * The format includes the task's completion status (1 for done, 0 for not done)
     * and its description.
     *
     * @return a String formatted for saving the task's data
     */
    public String toSaveData() {
        if (this.isDone) {
            return "1 | " + tagsToString() + "| " + description;
        } else {
            return "0 | " + tagsToString() + "| " + description;
        }
    }

    /**
     * Returns a String representation of the tags associated with this task.
     * The format includes "#" symbols at the front of the tags.
     *
     * @return a String representation of the tags
     */
    public String tagsToString() {
        if (tags.isEmpty()) {
            return " ";
        }

        StringBuilder tagsString = new StringBuilder();
        for (String tag : tags) {
            tagsString.append("#").append(tag).append(" ");
        }

        return tagsString.toString();
    }
}
