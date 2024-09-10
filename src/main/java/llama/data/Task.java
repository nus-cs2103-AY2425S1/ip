package llama.data;

import java.util.Objects;

/**
 * Represents a task to be done
 */
public abstract class Task {
    private boolean isComplete;
    private String description;

    /**
     * Creates a Task Object
     *
     * @param description description for task to be done
     * @param isDone true if task is done; else false
     */
    public Task(String description, boolean isDone) {
        assert !description.isEmpty();
        this.description = description;
        if (isDone) {
            markDone();
        } else {
            markNotDone();
        }
    }

    /**
     * Marks the task as done
     */
    public void markDone() {
        this.isComplete = true;
    }

    /**
     * Marks the task as not done
     */
    public void markNotDone() {
        this.isComplete = false;
    }

    /**
     * Converts Task into a string that can be saved in a file
     *
     * @return String in the correct format for file saving
     */
    public String getSaveTaskString() {
        if (this.isComplete) {
            return "|1|" + description;
        }

        return "|0|" + description;
    }

    @Override
    public String toString() {
        if (this.isComplete) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }

    /**
     * Search task description to see if it contains given keyword
     * Ignores case of search string
     *
     * @param searchStr keyword to search task description with
     * @return true if keyword found; else false
     */
    public boolean contains(String searchStr) {
        return this.description.toLowerCase().contains(searchStr.toLowerCase());
    }
}
