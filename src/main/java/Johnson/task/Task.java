package Johnson.task;

import Johnson.utils.Tag;

import java.util.ArrayList;

/**
 * Represents an abstract task. A task has a name and a completion status, as well as optional tags.
 * Subclasses of Task include Event, Deadline, and Todo.
 */
public abstract class Task {
    private String taskName;
    private boolean isDone;

    private ArrayList<Tag> tags;
    /**
     * Constructs a Task with the specified name.
     * May have tags.
     *
     * @param taskName the name of the task.
     * @param tags the tags of the task.
     */
    public Task(String taskName, String... tags) {
        this.taskName = taskName;
        this.isDone = false;
        this.tags = new ArrayList<>();
        if (tags != null) {
            for (String tag : tags) {
                this.tags.add(new Tag(tag));
            }
        }
    }

    /**
     * Constructs a Task with no name.
     */
    public Task() {
        this.taskName = null;
        this.isDone = false;
        this.tags = new ArrayList<>();
    }

    /**
     * Constructs a Task with the specified name and completion status.
     *
     * @param taskName the name of the task.
     * @param isDone the completion status of the task.
     */
    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
        this.tags = new ArrayList<>();
    }

    /**
     * Marks the task as complete.
     *
     * @return true if the task is marked as complete.
     */
    public boolean completeTask() {
        isDone = true;
        assert isDone : "Task should be marked as done";
        return isDone;
    }

    /**
     * Marks the task as incomplete.
     *
     * @return true if the task is marked as incomplete.
     */
    public boolean uncompleteTask() {
        isDone = false;
        assert !isDone : "Task should be marked as not done";
        return isDone;
    }
    public String getTaskName() {
        return taskName;
    }

    public boolean getTaskStatus() {
        return isDone;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setTaskStatus(boolean bool) {
        isDone = bool;
    }

    /**
     * checks if the task has the specified tag.
     * @param tag the tag to check for.
     * @return true if the task has the specified tag, false otherwise.
     */
    public boolean hasTag(String tag) {
        return tags.stream().anyMatch(t -> t.getTag().equals(tag));
    }

    /**
     * adds a tag to the task.
     * @param tag the tag to add.
     */
    public void addTag(String tag) {
        tags.add(new Tag(tag));
    }

    /**
     * returns the tags of the task.
     */
    public ArrayList<Tag> getTags() {
        return tags;
    }
    /**
     * Returns a string representation of the task.
     *
     * @return a string representation of the task.
     */
    @Override
    public String toString() {
        String res;
        if (isDone) {
            res = "[X] ";
        } else {
            res = "[ ] ";
        }
        return res + taskName + " " + tags;
    }
}
