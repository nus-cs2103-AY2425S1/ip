package data;

/**
 * Represents a task to be done
 */
public abstract class Task {
    private boolean completed;
    private String description;

    /**
     * Constructor for Task
     *
     * @param description description for task to be done
     * @param isDone true if task is done; else false
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        if (isDone) {
            markDone();
        } else {
            markNotDone();
        }
    }

    /**
     * Method to mark the task as done
     */
    public void markDone() {
        this.completed = true;
    }

    /**
     * Method to mark the task as not done
     */
    public void markNotDone() {
        this.completed = false;
    }

    /**
     * Method that converts Task into a string that can be saved in a file
     *
     * @return String in the correct format for file saving
     */
    public String getSaveTaskString() {
        if (completed) {
            return "|1|" + description;
        }

        return "|0|" + description;
    }

    @Override
    public String toString() {
        if (completed) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }
}
