package alex.task;

import java.time.LocalDate;

/**
 * Represents an action to be done. A Task object corresponds to
 * a piece of work represented by a string e.g., read book
 */
public class Task {
    public String description;
    public boolean isDone;
    public LocalDate dueDate;
    public String tag;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tag = "";
    }

    /**
     * Returns a string based on whether the task is completed.
     *
     * @return an icon representing the status of the task.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }
    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }
    /**
     * Marks the task as not completed.
     */
    public void unmark() {
        this.isDone = false;
    }
    /**
     * Returns a string that has the format to be saved in the file.
     *
     * @return string to be saved as data.
     */
    public void addTag(String tag) {
        this.tag = tag;
    }
    public String toBeSavedAsData() {
        return toString();
    }
    /**
     * Returns a string that has the format to be shown to the user.
     *
     * @return string to be displayed to users.
     */
    public String toString() {
        if (tag.isEmpty()) {
            return description;
        } else {
            return description + " #" + tag;
        }
    }

}
