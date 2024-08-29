package elster.tasks;

/**
 * Parent class that determines base behaviour of all tasks, primarily that of having a description
 * and status.
 */
public abstract class Task {
    protected String description;
    protected boolean status;

    /**
     * Is a general constructor for all tasks.
     * Note that all tasks have a description and a status.
     *
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.status = false;
    }

    protected static void printLine() {
        System.out.println("    ____________________________________________________________________________");
    }

    /**
     * Marks the task as done.
     * The method returns false if the task was already done.
     *
     * @return Boolean that represents whether the task was already done or not.
     */
    public boolean markAsDone() {
        if (this.status) {
            return false;

        } else {
            this.status = true;
            return true;
        }
    }

    /**
     * Marks the task as undone.
     * The method returns false if the task was already undone.
     *
     * @return Boolean that represents whether the task was already undone or not.
     */
    public boolean unmarkAsUndone() {
        if (!this.status) {
            return false;

        } else {
            this.status = false;
            return true;
        }
    }

    @Override
    public String toString() {
        if (this.status) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }

    /**
     * Returns a string representation of the task to be written to the save file.
     *
     * @return String representation of task suitable for writing to file.
     */
    public abstract String toFileString();
}
